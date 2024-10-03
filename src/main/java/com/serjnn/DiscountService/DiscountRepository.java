package com.serjnn.DiscountService;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DiscountRepository extends ReactiveCrudRepository
        <DiscountEntity, Long> {


}
