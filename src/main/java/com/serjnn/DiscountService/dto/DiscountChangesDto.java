package com.serjnn.DiscountService.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@NoArgsConstructor
public class DiscountChangesDto {
    private long productId;
    private double newDiscount;
    private Double prevDiscount;

    public DiscountChangesDto(long productId, double newDiscount) {
        this.productId = productId;
        this.newDiscount = newDiscount;
    }

    public DiscountChangesDto(long productId, double newDiscount, Double prevDiscount) {
        this.productId = productId;
        this.newDiscount = newDiscount;
        this.prevDiscount = prevDiscount;
    }
}
