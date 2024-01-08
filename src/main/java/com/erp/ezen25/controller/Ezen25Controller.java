package com.erp.ezen25.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class Ezen25Controller {
    @GetMapping("/main")
    public String mainView() {
        return "ezen25/login";
    }
}
