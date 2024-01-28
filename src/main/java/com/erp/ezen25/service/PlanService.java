package com.erp.ezen25.service;

import com.erp.ezen25.dto.BrandDTO;
import com.erp.ezen25.dto.planDTOs.*;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Plan;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.OrderRepository;
import com.erp.ezen25.repository.PlanRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final OrderRepository orderRepository;
    public List<PlanListResponseDTO> getPlanList () {
        List<Plan> pList = planRepository.findAllByOrderByCompleteDateDesc();

        return pList.stream()
                .map(PlanListResponseDTO::new)
                .toList();
    }

    public List<PbListResponseDTO> addFormOptionList () {
        return productRepository.findAllByOrderByProductNameAsc().stream()
                .map(PbListResponseDTO::new)
                .toList();
    }

    public void addPlan(String planList, LocalDate planDate) throws Exception {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(planList);

        List<Plan> planDTOList = new ArrayList<>();

        for (int i =0; i< jsonArray.size(); i++) {
            String productId = (String)((JSONObject)jsonArray.get(i)).get("productId");
            String brandId = (String)((JSONObject)jsonArray.get(i)).get("brandId");
            String planNumber = (String)((JSONObject)jsonArray.get(i)).get("planNumber");

            PlanAddRequestDTO planAdd = new PlanAddRequestDTO();
            planAdd.setCompleteDate(planDate);
            planAdd.setBrand(getBrandById(Long.parseLong(brandId)));
            planAdd.setProductInfo(getProdById(Long.parseLong(productId)));
            planAdd.setPlanNumber(Long.parseLong(planNumber));

            Plan PlanPart = planAdd.toEntity();
            planDTOList.add(PlanPart);
        }
        planRepository.saveAll(planDTOList);
    }

    public Brand getBrandById (Long id) {
        return brandRepository.getReferenceById(id);
    }
    public Product_Info getProdById (Long id) {
        return productRepository.getReferenceById(id);
    }
    public List<PlanAddOrderListResponseDTO> getOrderAndStock() {
        return orderRepository.findOrderAndStockList().stream()
                .map(PlanAddOrderListResponseDTO::new)
                .toList();
    }

    public void planDelete(Long planId) {
        planRepository.deleteById(planId);
    }

    public void planModify(Long planNum, Long planId) {
        PlanModifyRequestDTO pModi = planRepository.findById(planId).map(PlanModifyRequestDTO::new).get();
        pModi.setPlanNumber(planNum);
        planRepository.save(pModi.toEntity());
    }
}
