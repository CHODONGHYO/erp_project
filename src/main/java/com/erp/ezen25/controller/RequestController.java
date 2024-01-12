package com.erp.ezen25.controller;

import com.erp.ezen25.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
// 요청 관련 Controller
public class RequestController {
    private final RequestService requestService;


}
