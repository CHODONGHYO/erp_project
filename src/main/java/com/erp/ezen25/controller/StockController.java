package com.erp.ezen25.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
@Log4j2
// 재고 관련 Controller
public class StockController {

    // 재고리스트 페이지로 이동
    @GetMapping("/list")
    public String stockList() {
        return "ezen25/stock/stockList";
    }

    // 재고불출 페이지로 이동(모달)
    @GetMapping("/withdrawal")
    public String withdrawalGET() {
        return "ezen25/stock/withdrawal";
    }

    // 재고불출 페이지에서 출고하기 버튼 클릭 시 처리
    @PostMapping("/withdrawal")
    public String withdrawalPOST() {
        return "";
    }

    // 출고처리 페이지로 이동
    @GetMapping("/export")
    public String exportGET() {
        return "ezen25/stock/export";
    }

    // 출고처리 페이지에서 확인 버튼 클릭 시 처리 (alert창 추가)
    @PostMapping("/export")
    public String exportPOST() {
        return "ezen25/stock/exportList";
    }

    // 출고리스트 페이지로 이동
    @GetMapping("/exportList")
    public String exportListGET() {
        return "ezen25/stock/exportList";
    }
}
