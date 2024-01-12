package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Brand;
import lombok.Getter;

@Getter
public class BrandNameListResponseDTO {
    private String brandName;

    public BrandNameListResponseDTO (Brand brand) {
        this.brandName = brand.getBrandName();
    }
}
