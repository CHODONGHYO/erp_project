package com.erp.ezen25.controller;

import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ezen25/brand")
@Log4j2
@RequiredArgsConstructor
// 협력업체 관련 Controller
public class PartnerController {
    private  final BrandService brandService;

    @GetMapping("/")
    public String brandHome() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public void brandList(PageRequestDTO requestDTO, Model model) {

        log.info("brandList. ");
        log.info(requestDTO);

        model.addAttribute("brand", brandService.getList(requestDTO));
    }
}
