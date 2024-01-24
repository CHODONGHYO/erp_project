package com.erp.ezen25.dto.planDTOs;

import com.erp.ezen25.entity.Product_Info;
import lombok.Getter;

@Getter
public class PbListResponseDTO {
    private final Long productId;
    private final String productName;
    private final Long brandId;
    private final String brandName;

    public PbListResponseDTO(Product_Info pInfo) {
        this.productId= pInfo.getProductId();
        this.productName= pInfo.getProductName();
        this.brandId=pInfo.getBrand().getBrandId();
        this.brandName=pInfo.getBrand().getBrandName();
    }
}
