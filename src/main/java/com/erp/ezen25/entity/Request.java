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
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestId")
    private Long requestId;

    @Column(nullable = false)
    private LocalDateTime requestDate;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long requestNum;

    @Column(length = 1000)
    private String requestDescription;

    @Column(nullable = false)
    private LocalDateTime requestOutDate;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String requestStatus;

    @Column(nullable = false)
    private Long brandId;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'0'")
    private String requestCode;

}
