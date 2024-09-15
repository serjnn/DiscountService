package com.serjnn.DiscountService;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DiscountController {

    private final DiscountRepository discountRepository;


    @GetMapping
    List<DiscountEntity> getAll(){
        return discountRepository.findAll();
    }

    @PostMapping("/add")
    void add(@RequestBody DiscountEntity discountEntity){
        discountRepository.save(discountEntity);
    }
}
