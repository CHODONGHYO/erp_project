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
@Table(name = "export")
public class Export {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exportId")
    private Long exportId;

    @ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)

    private Product_Info productId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long exportNum;

    @Column(nullable = false)
    private String exportDate;


    @Column(length = 1000, nullable = false)
    private String orderCode;

    public void changeProductId(Product_Info productId) {
        this.productId = productId;
    }

    public void changeExportNum(Long exportNum) {
        this.exportNum = exportNum;
    }

    public void changeExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public void changeOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Export setExportId(Long exportId) {
        this.exportId = exportId;
        return this;
    }
}
