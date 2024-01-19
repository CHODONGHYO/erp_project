package com.erp.ezen25.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalDTO {

    private Long memberId;
    private String name;
    private Long orderId;
    private String orderCode;
    private Long productId;
    private String image;
    private String productName;
    private String mCategory;
    private String sCategory;
    private Long orderNum;

}
