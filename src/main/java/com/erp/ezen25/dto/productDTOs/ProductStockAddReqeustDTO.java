package com.erp.ezen25.dto.productDTOs;

import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.entity.Product_Stock;
import lombok.Data;

@Data
public class ProductStockAddReqeustDTO {
    private Product_Info product;
    private Member member;

    public Product_Stock toEntity () {
        return Product_Stock.builder()
                .product(product)
                .member(member)
                .build();
    }
}
