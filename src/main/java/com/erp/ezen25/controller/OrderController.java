package com.erp.ezen25.controller;

import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.service.ExportService;
import com.erp.ezen25.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/ezen25/order")
@Log4j2
@RequiredArgsConstructor
// 발주 관련 Controller
public class OrderController {

    private final OrderService orderService;

    private final ExportService exportService;

    @GetMapping("/list")
    public String orderList(Model model) {
        log.info("발주요청목록 페이지로 이동........");
        List<OrderDTO> orderList = orderService.getList();
        System.out.println(orderList);
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderList";
    }

    // export 관련 Controller
    @GetMapping("/export")
    public String exportListGet() {
        return "redirect:/ezen25/order/export/list";
    }

    @GetMapping("/export/list")
    public void exportList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("requestList. ");
        log.info(pageRequestDTO);

        model.addAttribute("exportResult", exportService.getList(pageRequestDTO));
    }

    @GetMapping("/export/register")
    public void exportRegister(Model model) {
        log.info("GET 형식 Register");

        LocalDateTime LDTCT = LocalDateTime.now();
        LocalDateTime LDTCT3 = LDTCT.plusDays(3);
        // 원하는 형식으로 포맷을 지정합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 형식에 맞게 날짜와 시간을 문자열로 변환합니다.
        String currentTime = LDTCT.format(formatter);
        String currentTimeplus3 = LDTCT3.format(formatter);

        model.addAttribute("Now", currentTime);
        model.addAttribute("Now3", currentTimeplus3);

    }

    @PostMapping("/export/register")
    public String exportRegisterPOST(ExportDTO exportDTO) {
        log.info("POST 형식 Register");

        exportService.register(exportDTO);

        return "redirect:/ezen25/order/export/list";

    }

    @GetMapping({"/export/read", "/export/modify", "/export/print"})
    public void exportRead(@RequestParam("exportId") Long exportId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("Get Read/Modify. exportId : " + exportId);

        ExportDTO e = exportService.read(exportId);

        model.addAttribute("exportDTO", e);
    }

    @PostMapping("/export/remove")
    public String exportRemove(@RequestParam("exportId") Long exportId) {
        log.info("Post Remove. exportId : " + exportId);

        exportService.remove(exportId);

        return "redirect:/ezen25/order/export/list";
    }

    @PostMapping("/export/modify")
    public String exportModify(@ModelAttribute("exportDTO") ExportDTO exportDTO,
                               @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                               RedirectAttributes redirectAttributes) {
        log.info("POST exportModify");

        exportService.modify(exportDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("exportId", exportDTO.getExportId());

        return "redirect:/ezen25/order/export/read";
    }
}
