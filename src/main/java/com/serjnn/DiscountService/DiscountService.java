package com.serjnn.DiscountService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;


    private Mono<Void> save(DiscountEntity discountEntity) {
        return discountRepository.save(discountEntity).then();

    }

    public Mono<Void> addDiscount(DiscountEntity discountEntity) {
        return discountRepository.findByProductId(discountEntity.getProductId())
                .flatMap(existingDiscountEntity -> {
                    double newDiscount = discountEntity.getDiscount();
                    if (Double.compare(existingDiscountEntity.getDiscount(), newDiscount) < 0) {
                        notifyProductService(discountEntity);
                    }
                    existingDiscountEntity.setDiscount(newDiscount);
                    //then() operator ignores previous result and returns what its argument, so here if we dont use it
                    // with Mono.just(existingDiscountEntity) as argument, switchIfEmpty operator will receive
                    // Mono<Void> from  save(existingDiscountEntity) but not Mono.just(existingDiscountEntity)
                    return save(existingDiscountEntity).then(Mono.just(existingDiscountEntity));
                })
                .switchIfEmpty(Mono.defer(() -> save(discountEntity).then(Mono.just(discountEntity))))
                .flatMap(this::save);
    }

    private void notifyProductService(DiscountEntity discountEntity) {
        //TODO
    }

}
