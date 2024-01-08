package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long planId;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long planNumber = 0L;

    @Column(nullable = false)
    private LocalDateTime completeDate = now();

    @Column(length = 1000, nullable = false)
    private String planStatus = "미정";
}
