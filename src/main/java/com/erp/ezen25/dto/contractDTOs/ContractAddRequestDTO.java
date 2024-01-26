package com.erp.ezen25.dto.contractDTOs;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Contract;
import com.erp.ezen25.entity.Product_Info;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContractAddRequestDTO {

    private Product_Info productInfo;
    private Brand brand;
    private String contractFile;
    private LocalDate contractDate;

    public Contract toEntity() {
        return Contract.builder()
                .productInfo(productInfo)
                .Brand(brand)
                .contractFile(contractFile)
                .contractDate(contractDate)
                .build();
    }
}
