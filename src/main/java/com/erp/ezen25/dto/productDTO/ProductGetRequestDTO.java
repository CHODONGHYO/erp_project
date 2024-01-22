package com.erp.ezen25.dto.productDTO;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.service.BrandService;
import lombok.Data;

@Data
public class ProductGetRequestDTO {

    private String productName;
    private String productDescription;
    private Brand brand;
    private String mCategory;
    private String sCategory;
    private Long originalPrice;
    private Long sellPrice;
    private String image;

    public Product_Info toEntity () {

        return Product_Info.builder()
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
