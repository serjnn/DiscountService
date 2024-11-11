package com.serjnn.DiscountService.contoller;


import com.serjnn.DiscountService.service.DiscountService;
import com.serjnn.DiscountService.model.DiscountEntity;
import com.serjnn.DiscountService.repositoty.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DiscountController {
    private final DiscountRepository discountRepository;

    private final DiscountService discountService;

    @GetMapping("/all")
    Flux<DiscountEntity> getAll(){
        return discountRepository.findAll();
    }

    @PostMapping("/add")
    Mono<Void> add(@RequestBody DiscountEntity discountEntity){
        return discountService.addDiscount(discountEntity);
    }
}
