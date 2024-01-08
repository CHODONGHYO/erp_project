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
@Table(name = "export")
public class Export {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long exportId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long exportNum = 0L;

    @Column(nullable = false)
    private LocalDateTime exportDate = now();

    @Column(length = 1000, nullable = false)
    private String exportStatus = "미정";

    @Column(length = 1000, nullable = false)
    private String orderCode;

}
