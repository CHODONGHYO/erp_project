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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestId")
    private Long requestId;

    @Column(nullable = false)
    private String requestDate;

    /*@ManyToOne(targetEntity = Product_Info.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")*/
    private Long productId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long requestNum;

    @Column(length = 1000)
    private String requestDescription;

    @Column(nullable = false)
    private String requestOutDate;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String requestStatus;

    /* @ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
     @JoinColumn*/
    /*@ColumnDefault("0L")*/
    private Long brandId;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'0'")
    private String requestCode;

    public void changeProductId(Long productId) {
        this.productId = productId;
    }

    public void changeRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public void changeRequestOutDate(String requestOutDate) {
        this.requestOutDate = requestOutDate;
    }

    public void changeRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void changeRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public void changeRequestNum(Long requestNum) {
        this.requestNum = requestNum;
    }

    public void changeBrandId(Long brandId) {
        this.brandId = brandId;
    }
    public void changeRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
