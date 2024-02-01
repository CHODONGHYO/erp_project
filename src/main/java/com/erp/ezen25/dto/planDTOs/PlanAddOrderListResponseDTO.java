package com.erp.ezen25.dto.planDTOs;

import com.erp.ezen25.entity.Order;
import com.erp.ezen25.queryMapping.OrderAndStockMapping;
import lombok.Getter;

@Getter
public class PlanAddOrderListResponseDTO {
    private final String productName;
    private final Long orderNum;
    private final Long productNum;

    public PlanAddOrderListResponseDTO(OrderAndStockMapping oasMap) {
        this.productName=oasMap.getProduct_Name();
        this.orderNum=oasMap.getOrder_Num();
        this.productNum=oasMap.getProduct_Num();
    }
}
