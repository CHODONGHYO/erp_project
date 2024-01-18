package com.erp.ezen25.dto;

import com.erp.ezen25.queryMapping.SCategoryListMapping;
import lombok.Getter;

@Getter
public class ProductSCateListResponseDTO {
    private final String s_category;

    public ProductSCateListResponseDTO(SCategoryListMapping scMap) {
        this.s_category = scMap.getS_category();
    }
}
