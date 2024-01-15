package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Brand;
import lombok.Getter;

@Getter
public class BrandNameListResponseDTO {
    private Long brandId;
    private String brandName;

    public BrandNameListResponseDTO (Brand brand) {
        this.brandId = brand.getBrandId();
        this.brandName = brand.getBrandName();
    }
}
