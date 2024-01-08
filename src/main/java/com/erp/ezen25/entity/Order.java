package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long orderNum;

    @Column(length = 1000)
    private String orderDescription;

    @Column(nullable = false)
    private LocalDateTime orderOutDate;

    @Column(length = 1000, nullable = false)
    private String orderStatus;

    @Column(length = 1000, nullable = false, unique = true)
    private String orderCode;
}
