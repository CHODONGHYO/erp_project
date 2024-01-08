package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "importcheck")
public class ImportCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importCheckId")
    private Long importCheckId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long requestNum;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String importCheckStatus;

    @Column(length = 1000, nullable = false, unique = true)
    private String requestCode;
}
