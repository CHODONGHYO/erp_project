package com.erp.ezen25.controller;

import com.erp.ezen25.dto.planDTOs.PbListResponseDTO;
import com.erp.ezen25.dto.planDTOs.PlanAddRequestDTO;
import com.erp.ezen25.dto.planDTOs.PlanListResponseDTO;
import com.erp.ezen25.entity.Plan;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.common.function.type4.Parser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity<Void> planAdd(@RequestParam("inputPlanList") String planList, @RequestParam("planDate") LocalDate planDate) throws Exception{
        planService.addPlan(planList, planDate);
        return ResponseEntity.ok().build();
    }
}
