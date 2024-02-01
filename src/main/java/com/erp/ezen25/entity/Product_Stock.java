package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name = "product_stock")
public class Product_Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pNumId")
    private Long pNumId;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Product_Info product;

    @ColumnDefault("0")
    private Long productNum;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ColumnDefault("0")
    private Long totalPrice;

}
