package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "exportcheck")
public class ExportCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exportCheckId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long orderNum;

    @Column(length = 1000, nullable = false)
    private String exportCheckStatus;

    @Column(length = 1000, nullable = false, unique = true)
    private String orderCode;
}
