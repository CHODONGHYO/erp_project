package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractId")
    private Long contractId;

    @Column(length = 1000, nullable = false)
    private String contractType;

    @Column(nullable = false)
    private Long contractTotalMoney;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    private LocalDateTime contractDate;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'없음'")
    private String contractFile;

}
