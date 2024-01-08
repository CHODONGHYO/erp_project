package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long orderId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private LocalDateTime orderDate = now();

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long orderNum = 0L;

    @Column(length = 1000)
    private String orderDescription;

    @Column(nullable = false)
    private LocalDateTime orderOutDate = now();

    @Column(length = 1000, nullable = false)
    private String orderStatus = "미정";

    @Column(length = 1000, nullable = false, unique = true)
    private String orderCode = "0";
}
