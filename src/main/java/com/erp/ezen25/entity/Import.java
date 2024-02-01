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
@Table(name = "import")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_id")
    private Long importId;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product_Info product;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long importNum;

    @Column(nullable = false)
    private String importDate;

    @Column(length = 1000, nullable = false)
    private String requestCode;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String importStatus;

    public void changeImportNum(Long importNum) {
        this.importNum = importNum;
    }

    public void changeProductId(Product_Info productId) {
        this.product = productId;
    }

    public void changeImportDate(String importDate) {
        this.importDate = importDate;
    }

    public void changeRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public void changeImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    public Import setImportId(Long importId) {
        this.importId = importId;
        return this;
    }
}
