package com.erp.ezen25.controller;

import com.erp.ezen25.dto.MemberDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.etc.MemberCreateForm;
import com.erp.ezen25.service.BrandService;
import com.erp.ezen25.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/ezen25/main")
// 메인 관련 Controller
public class MainController {
    private final MemberService memberService;
    private final BrandService brandService;

    @GetMapping({"", "/"})
    public String mainPage(PageRequestDTO pageRequestDTO, Model model) {
        log.info("brandList. ");
        log.info(pageRequestDTO);
        log.info("memberList");
        model.addAttribute("brandResult", brandService.getAllMembers());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("countMember", memberService.getNumberOfMembers());
        model.addAttribute("countBrand", brandService.getNumberOfBrands());
        return "/ezen25/main/mainPage";
    }

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm) {
        return "/ezen25/main/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/ezen25/main/signupForm";
        }

        if (!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않음");
            return "/ezen25/main/signupForm";
        }

        try {
            memberService.create(memberCreateForm.getUserId(), memberCreateForm.getPassword1(),
                    memberCreateForm.getEmail(), memberCreateForm.getName());
            return "redirect:/ezen25/main/";
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자임");
            return "/ezen25/main/signupForm";
        }
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
