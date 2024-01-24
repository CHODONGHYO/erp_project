package com.erp.ezen25.dto.productDTOs;

import com.erp.ezen25.entity.Brand;
import lombok.Getter;

@Getter
public class ProductBnameListResponseDTO {
    private final Long brandId;
    private final String brandName;

    public ProductBnameListResponseDTO(Brand brand) {
        this.brandId = brand.getBrandId();
        this.brandName = brand.getBrandName();
    }
}
