package com.erp.ezen25.controller;

import com.erp.ezen25.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    private final BrandService brandService;

    private final RequestService requestService;

    private final ImportService importService;

    private final ImportCheckService importCheckService;

    private final ProductService productService;

    private final MemberService memberService;

    @PostMapping("/brandSelectDelete")
    public String brandSelectDelete(@RequestParam("brandDeleteList") List<Long> brand_ids) {
        for (Long brandId : brand_ids) {
            brandService.remove(brandId);
        }
        return "redirect:/ezen25/brand/list?page=1";
    }

    @PostMapping("/memberSelectDelete")
    public String memberSelectDelete(@RequestParam("memberDeleteList") List<Long> member_ids) {
        for (Long memberId : member_ids) {
            memberService.remove(memberId);
        }
        return "redirect:/ezen25/brand/memberList?page=1";
    }

    @PostMapping("/requestSelectDelete")
    public String requestSelectDelete(@RequestParam("requestDeleteList") List<Long> request_ids) {
        for (Long requestId : request_ids) {
            requestService.remove(requestId);
        }
        return "redirect:/ezen25/request/list?page=1";
    }
    @PostMapping("/pListSelectDelete")
    public String pListSelectDelete(@RequestParam("pListDeleteList") List<Long> pList_ids) {
        for (Long pList_id : pList_ids) {
            productService.deleteProduct(pList_id);
        }
        return "redirect:/ezen25/request/pList?page=1";
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

    @PostMapping("/exportSelectDelete")
    public String exportSelectDelete(@RequestParam("exportDeleteList") List<Long> export_ids) {
        for (Long exportId : export_ids) {
            importService.remove(exportId);
        }
        return "redirect:/ezen25/order/export/list?page=1";
    }
}
