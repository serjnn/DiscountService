package com.serjnn.DiscountService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "discount_entity")
public class DiscountEntity {

    @Id
    private Long id;

    @Column("product_id")
    private Long productId;

    private Double discount;

}
