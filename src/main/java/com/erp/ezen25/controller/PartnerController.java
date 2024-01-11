package com.erp.ezen25.controller;

import com.erp.ezen25.dto.BrandDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ezen25/brand")
@Log4j2
@RequiredArgsConstructor
// 협력업체 관련 Controller
public class PartnerController {
    private  final BrandService brandService;

    @GetMapping("/")
    public String brandHome() {
        return "redirect:/ezen25/brand/list";
    }

    @GetMapping("/list")
    public void brandList(PageRequestDTO requestDTO, Model model) {

        log.info("brandList. ");
        log.info(requestDTO);

        model.addAttribute("result", brandService.getList(requestDTO));
    }

    @GetMapping("/register")
    public void brandRegister() {
        log.info("GET 형식 Register");
    }

    @PostMapping("/register")
    public String brandPOSTRegister(BrandDTO brandDTO, RedirectAttributes redirectAttributes) {
        log.info("POST 형식 Register");

        Long brandId = brandService.register(brandDTO);

        redirectAttributes.addFlashAttribute("msg", brandId);

        return "redirect:/ezen25/brand/list";

    }

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam("brandId") Long brandId, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("Get Read/Modify. gno : " + brandId);


        BrandDTO dto = brandService.read(brandId);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("brandId") Long brandId, RedirectAttributes redirectAttributes) {
        log.info("Post Remove. brandId : " + brandId);

        brandService.remove(brandId);

        redirectAttributes.addFlashAttribute("msg", brandId);

        return "redirect:/ezen25/brand/list";
    }

    @PostMapping("/modify")
    public String modify(BrandDTO brandDTO, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("Post Modify. brandDTO : " + brandDTO);

        brandService.modify(brandDTO);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("brandId", brandDTO.getBrandId());

        return "redirect:/ezen25/brand/read";
    }

}
