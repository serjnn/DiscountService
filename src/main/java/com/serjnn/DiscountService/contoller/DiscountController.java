package com.serjnn.DiscountService.contoller;


import com.serjnn.DiscountService.model.DiscountEntity;
import com.serjnn.DiscountService.repositoty.DiscountRepository;
import com.serjnn.DiscountService.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DiscountController {
    private final DiscountRepository discountRepository;
    private final DiscountService discountService;

    @GetMapping("/all")
    Flux<DiscountEntity> getAll() {
        return discountRepository.findAll();
    }

    @PostMapping("/add")
    Mono<Void> add(@RequestBody List<DiscountEntity> discountEntities) {
        System.out.println(discountEntities);
        return discountService.addDiscounts(discountEntities);
    }

    @GetMapping("/byProductId/{productId}")
    Mono<DiscountEntity> byProductId(@PathVariable("productId") long productId) {
        return discountService.findByProductId(productId);
    }
}
