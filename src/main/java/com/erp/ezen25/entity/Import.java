package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "import")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importId")
    private Long importId;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long importNum;

    @Column(nullable = false)
    private LocalDateTime importDate;

    @Column(length = 1000, nullable = false, unique = true)
    private String requestCode;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String importStatus;

}
