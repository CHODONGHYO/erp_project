package com.erp.ezen25.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
// 메인 관련 Controller
public class MainController {
    @GetMapping("/")
    public String mainView() {
        return "bootstrapHTML/index";
    }
}
