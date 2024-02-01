package com.erp.ezen25.dto.planDTOs;

import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Plan;
import com.erp.ezen25.entity.Product_Info;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PlanAddRequestDTO {
    private LocalDate completeDate;
    private Long planNumber;
    private Brand brand;
    private Product_Info productInfo;

    public Plan toEntity() {
        return Plan.builder()
                .completeDate(completeDate)
                .planNumber(planNumber)
                .brand(brand)
                .productInfo(productInfo)
                .build();
    }
}
