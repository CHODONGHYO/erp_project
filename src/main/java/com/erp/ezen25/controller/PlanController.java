package com.erp.ezen25.controller;

import com.erp.ezen25.dto.planDTOs.PbListResponseDTO;
import com.erp.ezen25.dto.planDTOs.PlanAddOrderListResponseDTO;
import com.erp.ezen25.dto.planDTOs.PlanListResponseDTO;
import com.erp.ezen25.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;

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
        List<PlanAddOrderListResponseDTO> oList = planService.getOrderAndStock();

        model.addAttribute("proList", pbList);
        model.addAttribute("oList", oList);
    }

    @PostMapping("/planAdd")
    @ResponseBody
    public ResponseEntity<Void> planAdd(@RequestParam("inputPlanList") String planList, @RequestParam("planDate") LocalDate planDate) throws Exception{
        planService.addPlan(planList, planDate);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/planDelete")
    @ResponseBody
    public ResponseEntity<Void> planDelete(@RequestParam("planId") Long planId) {
        planService.planDelete(planId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/planModify")
    @ResponseBody
    public ResponseEntity<Void> planModify(@RequestParam("planNum") Long planNum, @RequestParam("planId") Long planId) {
        System.out.println(planNum);
        System.out.println(planId);

        planService.planModify(planNum, planId);
        return ResponseEntity.ok().build();
    }
}
