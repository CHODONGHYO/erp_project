package com.erp.ezen25.dto.productDTOs;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Product_Info;
import lombok.Data;

@Data
public class ProductUpdateRequestDTO {
    private Long productId;
    private String productName;
    private Brand brand;
    private String mCategory;
    private String sCategory;
    private Long originalPrice;
    private Long sellPrice;
    private String image;
    private String productDescription;

    public Product_Info toEntity () {

        return Product_Info.builder()
                .productId(productId)
                .productName(productName)
                .productDescription(productDescription)
                .brand(brand)
                .mCategory(mCategory)
                .sCategory(sCategory)
                .originalPrice(originalPrice)
                .sellPrice(sellPrice)
                .image(image)
                .build();
    }
}
