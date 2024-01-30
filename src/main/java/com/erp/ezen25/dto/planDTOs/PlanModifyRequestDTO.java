package com.erp.ezen25.dto.planDTOs;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Plan;
import com.erp.ezen25.entity.Product_Info;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanModifyRequestDTO {
    private Long planId;
    private LocalDate completeDate;
    private Long planNumber;
    private Brand brand;
    private Product_Info productInfo;

    public PlanModifyRequestDTO(Plan plan) {
        this.planId=plan.getPlanId();
        this.completeDate=plan.getCompleteDate();
        this.planNumber=plan.getPlanNumber();
        this.brand=plan.getBrand();
        this.productInfo=plan.getProductInfo();
    }

    public Plan toEntity() {
        return Plan.builder()
                .planId(planId)
                .completeDate(completeDate)
                .planNumber(planNumber)
                .brand(brand)
                .productInfo(productInfo)
                .build();
    }
}
