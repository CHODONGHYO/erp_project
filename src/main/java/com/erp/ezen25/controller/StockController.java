package com.erp.ezen25.controller;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.service.OrderService;
import com.erp.ezen25.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/ezen25/stock")
@Log4j2
@RequiredArgsConstructor
// 재고 관련 Controller
public class StockController {

    private final StockService stockService;
    private final OrderService orderService;

    // 재고리스트 페이지로 이동
    @GetMapping("/list")
    public String stockList(Model model) {
        log.info("재고리스트 페이지로 이동........");
        List<StockDTO> stockList = stockService.getListWithProduct();
        model.addAttribute("stockList", stockList);
        return "ezen25/stock/stockList";
    }

    // 재고불출 페이지로 이동(모달)
    @GetMapping("/withdrawal")
    public String withdrawalGET(@RequestParam String orderCode, Model model) {
        log.info("재고불출 페이지로 이동........");
        List<WithdrawalDTO> withdrawalList = orderService.getWithdrawalList(orderCode);
        String name = orderService.getNameByOrderCode(orderCode);
        model.addAttribute("withdrawalList", withdrawalList);
        model.addAttribute("orderCode",orderCode);
        model.addAttribute("name",name);
        return "ezen25/stock/withdrawal";
    }

    // 재고불출 페이지에서 출고하기 버튼 클릭 시 처리
    @PostMapping("/withdrawal")
    public String withdrawalPOST(@RequestParam(value = "name") String name, Model model) {
        log.info("출고처리 페이지로 이동........");
        model.addAttribute("name",name);
        return "ezen25/stock/exporting";
    }

    // 출고처리 페이지에서 확인 버튼 클릭 시 처리 (alert창 추가)
    @PostMapping("/exporting")
    public String exportPOST() {
        return "ezen25/stock/exportList";
    }

    // 출고리스트 페이지로 이동
    @GetMapping("/exportList")
    public String exportListGET() {
        return "ezen25/stock/exportList";
    }
}
