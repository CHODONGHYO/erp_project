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
@Table(name = "product_info")
public class Product_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
    private String sCategory = "없음";

    @Column(nullable = false)
    private Long originalPrice = 0L;

    @Column(nullable = false)
    private Long sellPrice = 0L;

    @Column(length = 1000)
    private String image = "없는 이미지";
}
