package com.erp.ezen25.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
// 메인 관련 Controller
public class MainController {

    @GetMapping("/")
    public String loginGet() {
        return "ezen25/main/login";
    }
    @GetMapping("/main")
    public String mainGet() {
        return "ezen25/main/main";
    }

    @GetMapping("/charts")
    public String showCharts() {
        return "bootstrapHTML/charts";
    }

    @GetMapping("/error401")
    public String showError401() {
        return "bootstrapHTML/401";
    }

    @GetMapping("/error404")
    public String showError404() {
        return "bootstrapHTML/404";
    }
    @GetMapping("/error500")
    public String showError500() {
        return "bootstrapHTML/500";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "bootstrapHTML/login";
    }
    @GetMapping("/password")
    public String showPassword() {
        return "bootstrapHTML/password";
    }
    @GetMapping("/register")
    public String showRegister() {
        return "bootstrapHTML/register";
    }
    @GetMapping("/tables")
    public String showTables() {
        return "bootstrapHTML/tables";
    }

}
