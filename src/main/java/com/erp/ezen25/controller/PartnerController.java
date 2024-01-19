package com.erp.ezen25.controller;

import com.erp.ezen25.dto.BrandDTO;
import com.erp.ezen25.dto.ContractListResponseDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.service.BrandService;
import com.erp.ezen25.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ezen25/brand/*")
@Log4j2
@RequiredArgsConstructor
// 협력업체 관련 Controller
public class PartnerController {
    @Autowired
    private BrandService brandService;

    private final ContractService contractService;

    @GetMapping("/")
    public String brandHome() {
        return "redirect:/ezen25/brand/list";
    }

    @GetMapping("/list")
    public void brandList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("brandList. ");
        log.info(pageRequestDTO);

        model.addAttribute("brandResult", brandService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void brandRegister() {
        log.info("GET 형식 Register");
    }

    @PostMapping("/register")
    public String brandPOSTRegister(BrandDTO brandDTO) {
        log.info("POST 형식 Register");

        Long brandId = brandService.register(brandDTO);

        return "redirect:/ezen25/brand/list";

    }

    @GetMapping({"/read", "/modify"})
    public void brandRead(@RequestParam("brandId") Long brandId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("Get Read/Modify. gno : " + brandId);

        BrandDTO dto = brandService.read(brandId);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String brandRemove(@RequestParam("brandId") Long brandId, RedirectAttributes redirectAttributes) {
        log.info("Post Remove. brandId : " + brandId);

        brandService.remove(brandId);

        redirectAttributes.addFlashAttribute("msg", brandId);

        return "redirect:/ezen25/brand/list";
    }

    @PostMapping("/modify")
    public String brandModify(BrandDTO brandDTO, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("Post Modify. brandDTO : " + brandDTO);

        brandService.modify(brandDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("brandId", brandDTO.getBrandId());

        return "redirect:/ezen25/brand/read";
    }

    // 계약관리 -----------
    @GetMapping("/contract/contractList")
    public void contractList (Model model) {
        List<ContractListResponseDTO> cList = contractService.contractList();

        model.addAttribute("cList", cList);
    }
    @GetMapping("/contract/contractAdd")
    public void contractAddForm (Model model) {
    }
}
