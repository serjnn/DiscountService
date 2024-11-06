package com.serjnn.DiscountService;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DiscountRepository extends ReactiveCrudRepository
        <DiscountEntity, Long> {


    Mono<DiscountEntity> findByProductId(Long productId);


}
