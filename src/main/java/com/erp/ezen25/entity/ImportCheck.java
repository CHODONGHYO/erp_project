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
@Table(name = "importcheck")
public class ImportCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long importCheckId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long requestNum;

    @Column(length = 1000, nullable = false)
    private String importCheckStatus;

    @Column(length = 1000, nullable = false, unique = true)
    private String requestCode;
}
