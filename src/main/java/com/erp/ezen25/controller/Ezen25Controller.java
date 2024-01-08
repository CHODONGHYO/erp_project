package com.erp.ezen25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ezen25Controller {
    @GetMapping("/main")
    public String mainView() {
        return "ezen25/login";
    }
}
