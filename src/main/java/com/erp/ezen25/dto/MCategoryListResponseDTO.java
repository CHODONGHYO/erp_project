package com.erp.ezen25.dto;

import com.erp.ezen25.queryMapping.MCategoryListMapping;
import lombok.Getter;

@Getter
public class MCategoryListResponseDTO {
    String m_category;

    public MCategoryListResponseDTO(MCategoryListMapping mcMap) {
        this.m_category = mcMap.getM_category();
    }
}
