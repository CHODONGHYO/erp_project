package com.erp.ezen25.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
@Log4j2
// 재고 관련 Controller
public class StockController {

    @GetMapping("/list")
    public String stockList() {
        return "ezen25/stock/stockList";
    }




}
