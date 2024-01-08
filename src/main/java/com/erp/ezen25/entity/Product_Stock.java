package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "product_stock")
public class Product_Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long pNumId;

    @Column(nullable = false)
    private Long productId;

    private Long productNum = 0L;

    @Column(nullable = false)
    private Long memberId;

    private Long totalPrice = 0L;
}
