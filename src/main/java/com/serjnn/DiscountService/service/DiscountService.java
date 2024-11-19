package com.serjnn.DiscountService.service;


import com.serjnn.DiscountService.dto.DiscountDto;
import com.serjnn.DiscountService.kafka.KafkaSender;
import com.serjnn.DiscountService.model.DiscountEntity;
import com.serjnn.DiscountService.repositoty.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final KafkaSender kafkaSender;

    private Mono<Void> save(DiscountEntity discountEntity) {
        return discountRepository.save(discountEntity).then();

    }

    public Mono<Void> addDiscounts(List<DiscountEntity> discountEntities) {
        return Flux.fromIterable(discountEntities)
                .flatMap(discountEntity ->
                        discountRepository.findByProductId(discountEntity.getProductId()) //place here 1 more bracket
                                .flatMap(existingDiscountEntity -> {
                                    double newDiscount = discountEntity.getDiscount();
                                    if (Double.compare(existingDiscountEntity.getDiscount(), newDiscount) < 0) {
                                        sendDiscountTidings(discountEntity);
                                    }

                                    existingDiscountEntity.setDiscount(newDiscount);
                                    return save(existingDiscountEntity).then(Mono.just(existingDiscountEntity));
                                })
                                .switchIfEmpty(Mono.defer(() -> save(discountEntity).then(Mono.just(discountEntity))))
                                .flatMap(newDiscountEntity -> {
                                    sendDiscountTidings(newDiscountEntity);
                                    return save(newDiscountEntity);
                                }))
                .then();
    }

    private void sendDiscountTidings(DiscountEntity discountEntity) {
        DiscountDto discountDto = new DiscountDto(discountEntity.getProductId(), discountEntity.getDiscount());
        kafkaSender.sendNewDiscount("newDiscountTopic", discountDto);

    }


}
