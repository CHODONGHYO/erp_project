package com.erp.ezen25.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ezen25/product")
public class ProductController {
    @GetMapping("/productList")
    public void getProductList () {
        System.out.println("상품리스트 도착");
    }
}
