package com.erp.ezen25.controller;

import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.RequestDTO;
import com.erp.ezen25.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/ezen25/request/*")
@Log4j2
@RequiredArgsConstructor
// 협력업체 관련 Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/")
    public String RequestHome() {
        return "redirect:/ezen25/request/list";
    }

    @GetMapping("/list")
    public void RequestList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("requestList. ");
        log.info(pageRequestDTO);

        model.addAttribute("requestResult", requestService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void requestRegister(Model model) {
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

    @PostMapping("/register")
    public String requestPOSTRegister(RequestDTO requestDTO) {
        log.info("POST 형식 Register");

        requestService.register(requestDTO);

        return "redirect:/ezen25/request/list";

    }

    @GetMapping({"/read", "/modify"})
    public void requestRead(@RequestParam("requestId") Long requestId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("Get Read/Modify. requestId : " + requestId);

        RequestDTO dto = requestService.read(requestId);

        model.addAttribute("requestdto", dto);
    }

    @PostMapping("/remove")
    public String requestRemove(@RequestParam("requestId") Long requestId) {
        log.info("Post Remove. requestId : " + requestId);

        requestService.remove(requestId);

        return "redirect:/ezen25/request/list";
    }

    @PostMapping("/modify")
    public String requestModify(@ModelAttribute("requestDTO") RequestDTO requestDTO,
                                @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                                RedirectAttributes redirectAttributes) {

        requestService.modify(requestDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("requestId", requestDTO.getRequestId());

        return "redirect:/ezen25/request/read";
    }
}
