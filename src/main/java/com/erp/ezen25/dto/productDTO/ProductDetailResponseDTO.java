package com.erp.ezen25.dto.productDTO;

import com.erp.ezen25.entity.Product_Info;
import lombok.Getter;

@Getter
public class ProductDetailResponseDTO {
    private final Long productId;
    private final String productName;
    private final String mCategory;
    private final String sCategory;
    private final Long originalPrice;
    private final Long sellPrice;
    private final String image;
    private final Long brandId;
    private final String brandName;
    private final String productDescription;
    private final String brandPhone;
    private final String brandEmail;

    public ProductDetailResponseDTO(Product_Info pInfo) {
        this.productId = pInfo.getProductId();
        this.productName = pInfo.getProductName();
        this.brandName = pInfo.getBrand().getBrandName();
        this.mCategory = pInfo.getMCategory();
        this.sCategory = pInfo.getSCategory();
        this.originalPrice = pInfo.getOriginalPrice();
        this.sellPrice = pInfo.getSellPrice();
        this.image = pInfo.getImage();
        this.productDescription = pInfo.getProductDescription();
        this.brandPhone = pInfo.getBrand().getBrandPhone();
        this.brandEmail = pInfo.getBrand().getBrandEmail();
        this.brandId = pInfo.getBrand().getBrandId();
    }
}
