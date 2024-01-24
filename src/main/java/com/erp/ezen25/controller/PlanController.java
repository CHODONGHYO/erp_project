package com.erp.ezen25.controller;

import com.erp.ezen25.dto.planDTOs.PbListResponseDTO;
import com.erp.ezen25.dto.planDTOs.PlanListResponseDTO;
import com.erp.ezen25.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ezen25/plan")
// 계획 관련 Controller
public class PlanController {

    private final PlanService planService;

    @GetMapping("/planList")
    public void planList(Model model) {
        List<PlanListResponseDTO> pList = planService.getPlanList();

        model.addAttribute("pList", pList);
    }

    @GetMapping("/planAdd")
    public void planAddForm(Model model) {
        List<PbListResponseDTO> pbList = planService.addFormOptionList();

        model.addAttribute("proList", pbList);
    }

    @PostMapping("/planAdd")
    @ResponseBody
    public ResponseEntity<Void> planAdd(@RequestParam("inputPlanList") String planList) {
        // System.out.println(paramMap.get("inputPlanList").getClass());
        System.out.println(planList);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        return ResponseEntity.ok().build();
    }
}
