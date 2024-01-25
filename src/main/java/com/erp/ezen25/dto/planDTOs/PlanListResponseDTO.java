package com.erp.ezen25.dto.planDTOs;

import com.erp.ezen25.entity.Plan;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PlanListResponseDTO {
    private final Long planId;
    private final LocalDate completeDate;
    private final String brandName;
    private final String productName;
    private final Long planNumber;
    private final String planStatus;

    public PlanListResponseDTO(Plan plan) {
        this.planId=plan.getPlanId();
        this.completeDate=plan.getCompleteDate();
        this.brandName=plan.getBrand().getBrandName();
        this.productName=plan.getProductInfo().getProductName();
        this.planNumber=plan.getPlanNumber();
        this.planStatus=plan.getPlanStatus();
    }

}
