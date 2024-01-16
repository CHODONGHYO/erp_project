package com.erp.ezen25.controller;

import com.erp.ezen25.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/ezen25/common/*")
@Log4j2
@RequiredArgsConstructor
public class CommonController {
    @Autowired
    private BrandService brandService;
    @PostMapping("/selectDelete")
    public String selectDelete(@RequestParam("brandDeleteList") List<Long> brand_ids) {
        for (Long brandId : brand_ids) {
            brandService.remove(brandId);
        }
        return "redirect:/ezen25/brand/list?page=1";
    }
}
