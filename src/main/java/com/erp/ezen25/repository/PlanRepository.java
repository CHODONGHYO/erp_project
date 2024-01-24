package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByOrderByCompleteDateDesc();
}
