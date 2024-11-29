package com.serjnn.DiscountService.service;


import com.serjnn.DiscountService.dto.DiscountChangesDto;
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
                                    double prevDiscount = existingDiscountEntity.getDiscount();
                                    sendDiscountChanges(new DiscountChangesDto(
                                            discountEntity.getProductId(),
                                            newDiscount,
                                            prevDiscount));


                                    existingDiscountEntity.setDiscount(newDiscount);
                                    return save(existingDiscountEntity).then(Mono.just(existingDiscountEntity));
                                })
                                .switchIfEmpty(
                                        save(discountEntity)
                                                .then(Mono.just(discountEntity))
                                                .doOnNext(newDiscount -> sendDiscountChanges(
                                                        new DiscountChangesDto(
                                                                discountEntity.getProductId(),
                                                                discountEntity.getDiscount()
                                                        )
                                                ))
                                )
                )
                .then();

    }

    private void sendDiscountChanges(DiscountChangesDto DiscountChangesDto) {
        kafkaSender.sendNewDiscount("discountChangesTopic", DiscountChangesDto);

    }

    public Mono<DiscountEntity> findByProductId(Long productId) {
        return discountRepository.findByProductId(productId);
    }
}
