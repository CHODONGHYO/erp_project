package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Product_Info;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDTO {

    private Long pNumId;
    private Long productId;
    private Long productNum;
    private Long memberId;
    private Long totalPrice;
    private String productName;
    private String mCategory;
    private String sCategory;
    private String originalPrice;
    private String sellPrice;
    private String image;
    private LocalDateTime importDate;
    private Product_Info product;

}
