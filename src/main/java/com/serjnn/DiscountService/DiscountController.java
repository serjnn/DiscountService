package com.serjnn.DiscountService;


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


    @GetMapping
    Flux<DiscountEntity> getAll(){
        return discountRepository.findAll();
    }

    @PostMapping("/add")
    Mono<Void> add(@RequestBody DiscountEntity discountEntity){
        return discountRepository.save(discountEntity).then();
    }
}
