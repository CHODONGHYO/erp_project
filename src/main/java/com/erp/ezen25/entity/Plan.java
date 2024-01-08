package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long planNumber;

    @Column(nullable = false)
    private LocalDateTime completeDate;

    @Column(length = 1000, nullable = false)
    private String planStatus;
}
