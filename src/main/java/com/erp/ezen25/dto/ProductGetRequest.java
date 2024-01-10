package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Product_Info;

public class ProductGetRequest {
    private String productName;
    private String productDescription;
    private Long brandId;
    private String mCategory;
    private String sCategory;
    private Long originalPrice;
    private Long sellPrice;
    private String image;

    public Product_Info toEntity () {
        return Product_Info.builder()
                .productName(productName)
                .productDescription(productDescription)
                .brand(brandId)
                .mCategory(mCategory)
                .sCategory(sCategory)
                .originalPrice(originalPrice)
                .sellPrice(sellPrice)
                .image(image)
                .build();
    }
}
