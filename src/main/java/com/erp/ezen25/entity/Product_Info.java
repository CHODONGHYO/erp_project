package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "product_info")
public class Product_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 1000, nullable = false, unique = true)
    private String productName;

    @Column(length = 1000, nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private Long brandId;

    @Column(length = 1000, nullable = false)
    private String mCategory;

    @Column(length = 1000)
    private String sCategory;

    @Column(nullable = false)
    private Long originalPrice;

    @Column(nullable = false)
    private Long sellPrice;

    @Column(length = 1000)
    private String image;
}
