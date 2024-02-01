package com.erp.ezen25.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalDTO {

    private String name;
    private String orderCode;
    private Long productId;
    private String image;
    private String productName;
    private String mCategory;
    private String sCategory;
    private Long orderNum;
    private String orderStatus;
    private Long productNum;

}
