package com.erp.ezen25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandDTO {
    private Long brandId;
    private String brandName;
    private String brandPhone;
    private String brandEmail;
    private String brandDescription;
}
