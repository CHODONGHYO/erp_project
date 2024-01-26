package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planId")
    private Long planId;

    @ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Product_Info productInfo;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long planNumber;

    @Column(nullable = false)
    private LocalDate completeDate;
}
