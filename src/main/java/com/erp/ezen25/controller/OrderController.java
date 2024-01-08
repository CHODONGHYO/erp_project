package com.erp.ezen25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
// 발주 관련 Controller
public class OrderController {

    @GetMapping("/list")
    public String orderList() {
        return "/ezen25/order/orderList";
    }


}
