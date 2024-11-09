package com.serjnn.DiscountService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class DiscountDto {
    private Long productId;
    private Double discount;
}
