package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Product_Info;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class StockDTO {

    private Long pNumId;
    private String image;
    private Long productId;
    private Long productNum;
    private String productName;
    private String mCategory;
    private String sCategory;
    private Long originalPrice;
    private Long sellPrice;
}
