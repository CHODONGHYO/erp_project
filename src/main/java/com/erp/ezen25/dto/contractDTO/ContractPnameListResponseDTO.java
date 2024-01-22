package com.erp.ezen25.dto.contractDTO;

import com.erp.ezen25.entity.Product_Info;
import lombok.Getter;

@Getter
public class ContractPnameListResponseDTO {
    private final Long productId;
    private final String productName;

    public ContractPnameListResponseDTO(Product_Info pInfo) {
        this.productId = pInfo.getProductId();
        this.productName = pInfo.getProductName();
    }
}
