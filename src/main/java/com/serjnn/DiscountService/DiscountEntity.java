package com.serjnn.DiscountService;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor

@Table(name = "discount_entity")
public class DiscountEntity {
    @Id
    private long id;

    Long productId;

    double discount;

    public DiscountEntity(Long productId, double discount) {
        this.productId = productId;
        this.discount = discount;
    }
}
