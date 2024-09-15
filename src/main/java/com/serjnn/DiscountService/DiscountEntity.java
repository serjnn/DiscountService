package com.serjnn.DiscountService;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "discount_entity")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    Long productId;

    double discount;

    public DiscountEntity(Long productId, double discount) {
        this.productId = productId;
        this.discount = discount;
    }
}
