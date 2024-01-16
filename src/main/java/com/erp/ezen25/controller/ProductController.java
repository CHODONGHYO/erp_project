package com.erp.ezen25.controller;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ezen25/product")
public class ProductController {


    private final ProductService productService;

    @GetMapping("/productList")
    public void getProductList (Model model) {
        List<ProductListResponseDTO> pList = productService.getproductList();

        model.addAttribute("pList", pList);
        System.out.println("상품리스트  도착");
    }

    @GetMapping("/productAdd")
    public void registerProductForm (Model model) {
        List<MCategoryListResponseDTO> mCateList = productService.getMCategoryList();
        List<SCategoryListResponseDTO> sCateList = productService.getSCategoryList();
        List<BrandNameListResponseDTO> brandList = productService.getBrandList();

        model.addAttribute("mCateList", mCateList);
        model.addAttribute("sCateList", sCateList);
        model.addAttribute("brandList", brandList);
        long time = System.currentTimeMillis();
        System.out.println(time);
    }


    @PostMapping("/productAdd")
    public String registerProduct(ProductGetRequestDTO getDto, @RequestParam("imageSelect") MultipartFile mf) throws IOException{
        productService.createProduct(getDto, mf);
        return "redirect:/ezen25/product/productList";
    }
}
