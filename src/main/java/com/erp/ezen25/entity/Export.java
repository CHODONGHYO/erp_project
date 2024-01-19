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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "export")
public class Export {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exportId")
    private Long exportId;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long exportNum;

    /*@Column(nullable = false)
    private LocalDateTime exportDate;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String exportStatus;*/

    @Column(length = 1000, nullable = false)
    private String orderCode;

}
