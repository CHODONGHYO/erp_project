package com.erp.ezen25.controller;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        List<ProductMCateListResponseDTO> mCateList = productService.getMCategoryList();
        List<ProductSCateListResponseDTO> sCateList = productService.getSCategoryList();
        List<ProductBnameListResponseDTO> brandList = productService.getBrandList();

        model.addAttribute("mCateList", mCateList);
        model.addAttribute("sCateList", sCateList);
        model.addAttribute("brandList", brandList);
    }
    @PostMapping("/productAdd")
    public String registerProduct(ProductGetRequestDTO getDto, @RequestParam("imageSelect") MultipartFile mf) throws IOException{
        productService.createProduct(getDto, mf);
        return "redirect:/ezen25/product/productList";
    }
    @PostMapping("/productDelete")
    public void deleteProduct(Long productId) {
        productService.deleteProduct(productId);
    }
    @GetMapping("/productDetail/{productId}")
    public String productDetail(@PathVariable("productId") Long productId, Model model) {
       ProductDetailResponseDTO productDetail = productService.productdetail(productId);
       model.addAttribute("product", productDetail);

       return "/ezen25/product/productDetail";
    }

    @GetMapping("/productModify/{productId}")
    public String productModifyForm(@PathVariable("productId") Long productId, Model model) {
        ProductDetailResponseDTO productDetail = productService.productdetail(productId);
        List<ProductMCateListResponseDTO> mCateList = productService.getMCategoryList();
        List<ProductSCateListResponseDTO> sCateList = productService.getSCategoryList();
        List<ProductBnameListResponseDTO> brandList = productService.getBrandList();

        model.addAttribute("product", productDetail);
        model.addAttribute("mCateList", mCateList);
        model.addAttribute("sCateList", sCateList);
        model.addAttribute("brandList", brandList);
        return "/ezen25/product/productModify";
    }
    @PostMapping("/productModify/{productId}")
    public String productModify(ProductUpdateRequestDTO updateRequest, @RequestParam("imageSelect") MultipartFile mf) throws IOException {
        productService.updateProduct(updateRequest, mf);
        return "redirect:/ezen25/product/productDetail/{productId}";
    };
}
