package com.erp.ezen25.controller;

import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@Log4j2
@RequiredArgsConstructor
// 발주 관련 Controller
public class OrderController {

    private final OrderService orderService;
    @GetMapping("/list")
    public String orderList(Model model) {
        log.info("발주요청목록 페이지로 이동........");
        List<OrderDTO> orderList = orderService.getList();
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderList";
    }

}
