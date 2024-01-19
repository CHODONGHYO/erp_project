package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "product_info")
public class Product_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(length = 1000, nullable = false, unique = true)
    private String productName;

    @Column(length = 1000, nullable = false)
    private String productDescription;

    @ManyToOne(targetEntity = Brand.class, fetch=FetchType.LAZY)
    @JoinColumn(name="brandId",nullable = false)
    private Brand brand;

    @Column(length = 1000, nullable = false)
    private String mCategory;

    @Column(length = 1000)
    @ColumnDefault("'없음'")
    private String sCategory;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long originalPrice;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long sellPrice;

    @Column(length = 1000)
    @ColumnDefault("'없음'")
    private String image;

    public Product_Info setProductId(Long productId) {
        this.productId = productId;
        return this;
    }
}
