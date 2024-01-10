package com.erp.ezen25.controller;

import com.erp.ezen25.dto.ProductListResponse;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ezen25/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/productList")
    public void getProductList (Model model) {
        List<ProductListResponse> pList = productService.getproductList().stream()
                .map(ProductListResponse::new)
                .toList();
        System.out.println(pList);
        model.addAttribute("pList", pList);
        System.out.println("상품리스트 도착");
    }

    @GetMapping("/productAdd")
    public void registerProduct () {
        
    }
}
