package com.serjnn.DiscountService.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountChangesDto {
    private Long productId;
    private Double newDiscount;
    private Double prevDiscount;

    public DiscountChangesDto(Long productId, Double newDiscount) {
        this.productId = productId;
        this.newDiscount = newDiscount;
    }
}
