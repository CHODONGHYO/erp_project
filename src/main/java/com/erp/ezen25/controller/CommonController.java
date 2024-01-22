package com.erp.ezen25.controller;

import com.erp.ezen25.service.BrandService;
import com.erp.ezen25.service.ImportCheckService;
import com.erp.ezen25.service.ImportService;
import com.erp.ezen25.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/ezen25/common/*")
@Log4j2
@RequiredArgsConstructor
public class CommonController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private ImportService importService;
    @Autowired
    private ImportCheckService importCheckService;

    @PostMapping("/brandSelectDelete")
    public String brandSelectDelete(@RequestParam("brandDeleteList") List<Long> brand_ids) {
        for (Long brandId : brand_ids) {
            brandService.remove(brandId);
        }
        return "redirect:/ezen25/brand/list?page=1";
    }
    @PostMapping("/requestSelectDelete")
    public String requestSelectDelete(@RequestParam("requestDeleteList") List<Long> request_ids) {
        for (Long requestId : request_ids) {
            requestService.remove(requestId);
        }
        return "redirect:/ezen25/request/list?page=1";
    }

    @PostMapping("/importSelectDelete")
    public String importSelectDelete(@RequestParam("importDeleteList") List<Long> import_ids) {
        for (Long importId : import_ids) {
            importService.remove(importId);
        }
        return "redirect:/ezen25/request/import/list?page=1";
    }

    @PostMapping("/icSelectDelete")
    public String icSelectDelete(@RequestParam("icDeleteList") List<Long> ic_ids) {
        for (Long icId : ic_ids) {
            importCheckService.remove(icId);
        }
        return "redirect:/ezen25/request/importCheck/list?page=1";
    }

    @PostMapping("/returnsSelectDelete")
    public String returnsSelectDelete(@RequestParam("returnsDeleteList") List<Long> returns_ids) {
        for (Long Id : returns_ids) {
            importCheckService.remove(Id);
        }
        return "redirect:/ezen25/request/returns/list?page=1";
    }
}
