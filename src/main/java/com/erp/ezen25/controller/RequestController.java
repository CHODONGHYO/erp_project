package com.erp.ezen25.controller;

import com.erp.ezen25.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ezen25/request")
// 요청 관련 Controller
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/list")
    public String requestList() {
        return "/ezen25/request/requestList";
    }
}
