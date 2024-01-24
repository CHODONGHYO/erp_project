package com.erp.ezen25.dto.productDTOs;

import com.erp.ezen25.entity.Product_Info;
import lombok.Getter;

@Getter
public class ProductListResponseDTO {
    private final Long productId;
    private final String productName;
    private final String mCategory;
    private final String sCategory;
    private final Long originalPrice;
    private final Long sellPrice;
    private final String image;
    private final String brandName;
    public ProductListResponseDTO(Product_Info pInfo) {
        this.productId = pInfo.getProductId();
        this.productName = pInfo.getProductName();
        this.brandName = pInfo.getBrand().getBrandName();
        this.mCategory = pInfo.getMCategory();
        this.sCategory = pInfo.getSCategory();
        this.originalPrice = pInfo.getOriginalPrice();
        this.sellPrice = pInfo.getSellPrice();
        this.image = pInfo.getImage();
    }
}
