package com.erp.ezen25.dto;

import com.erp.ezen25.entity.Contract;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ContractListResponseDTO {
    private final Long contractId;
    private final String brandName;
    private final String productName;
    private final LocalDate contractDate;
    private final String contractFile;

    public ContractListResponseDTO(Contract contract) {
        this.contractId= contract.getContractId();
        this.brandName =contract.getBrand().getBrandName();
        this.productName=contract.getProductInfo().getProductName();
        this.contractDate=contract.getContractDate();
        this.contractFile=contract.getContractFile();
    }
}
