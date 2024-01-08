package com.erp.ezen25.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/ezen25")
public class Ezen25Controller {
    @GetMapping("/login")
    public void loginGET() {
        log.info("로그인 페이지로");
    }

    @GetMapping("/main")
    public String main() {
        log.info("본사 메인페이지로");
        return "/ezen25/headquarters/main";
    }

    @GetMapping("/layout")
    public String layout() {
        return "/layout/layout";
    }
}
