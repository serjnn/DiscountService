package com.serjnn.DiscountService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "discount_entity")
public class DiscountEntity {
    @Id
    private long id;

    private Long productId;

    private double discount;

}
