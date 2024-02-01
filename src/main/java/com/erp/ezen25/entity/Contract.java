package com.erp.ezen25.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractId")
    private Long contractId;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="product_id", nullable = false)
    private Product_Info productInfo;

    @ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="brand_id", nullable = false)
    private Brand Brand;

    @Column(nullable = false)
    private LocalDate contractDate;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'없음'")
    private String contractFile;

}
