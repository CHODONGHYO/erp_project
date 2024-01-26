package com.erp.ezen25.controller;

import com.erp.ezen25.etc.MemberCreateForm;
import com.erp.ezen25.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/ezen25/main/*")
// 메인 관련 Controller
public class MainController {
    private final MemberService memberService;

    @GetMapping("/mainPage")
    public void mainPage() {

    }

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm) {
        return "ezen25/main/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signupForm";
        }

        if (!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않음");
            return "signupForm";
        }

        try {
            memberService.create(memberCreateForm.getUserId(), memberCreateForm.getPassword1(),
                    memberCreateForm.getEmail(), memberCreateForm.getName());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자임");
            return "signupForm";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signupForm";
        }

        return "ezen25/main/mainPage";
    }

    @GetMapping("/login")
    public String login() {
        return "ezen25/main/login";
    }

    @PostMapping("/loginProcess")
    public String postLogin() {
        return null;
    }



}
