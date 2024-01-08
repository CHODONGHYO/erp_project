package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "import")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long importId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long importNum = 0L;

    @Column(nullable = false)
    private LocalDateTime importDate = now();

    @Column(length = 1000, nullable = false, unique = true)
    private String requestCode;

    @Column(length = 1000, nullable = false)
    private String importStatus = "미정";

}
