package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Contract;
import com.erp.ezen25.entity.Product_Info;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractAddRequestDTO {
    private Product_Info productInfo;
    private Brand brand;
    private String contractFile;

    public Contract toEntity() {
        return Contract.builder()
                .productInfo(productInfo)
                .Brand(brand)
                .contractFile(contractFile)
                .build();
    }
}
