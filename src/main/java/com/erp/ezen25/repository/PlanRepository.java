package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByOrderByCompleteDateDesc();

    List<Plan> findAllByCompleteDateBetween(LocalDate date1, LocalDate date2);
    List<Plan> findAllByCompleteDateLessThanEqualOrderByCompleteDateDesc(LocalDate date);
    List<Plan> findAllByCompleteDateGreaterThanEqual(LocalDate date);
}
