package com.erp.ezen25.service;

import com.erp.ezen25.dto.planDTOs.PbListResponseDTO;
import com.erp.ezen25.dto.planDTOs.PlanListResponseDTO;
import com.erp.ezen25.entity.Plan;
import com.erp.ezen25.repository.PlanRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final ProductRepository productRepository;
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
}
