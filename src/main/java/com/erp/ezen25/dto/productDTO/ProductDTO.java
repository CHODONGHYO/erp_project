package com.erp.ezen25.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Long brandId;
    private String mCategory;
    private String sCategory;
    private Long originalPrice;
    private Long sellPrice;
    private String image;
}
