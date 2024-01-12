package com.erp.ezen25.dto;

import com.erp.ezen25.queryMapping.SCategoryListMapping;
import lombok.Getter;

@Getter
public class SCategoryListResponseDTO {
    String s_category;

    public SCategoryListResponseDTO(SCategoryListMapping scMap) {
        this.s_category = scMap.getS_category();
    }
}
