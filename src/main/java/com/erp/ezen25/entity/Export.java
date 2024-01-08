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
@Table(name = "export")
public class Export {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exportId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long exportNum;

    @Column(nullable = false)
    private LocalDateTime exportDate;

    @Column(length = 1000, nullable = false)
    private String exportStatus;

    @Column(length = 1000, nullable = false)
    private String orderCode;



}
