package com.erp.ezen25.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProductController {
    @GetMapping
    public void getProductList () {

    }
}
