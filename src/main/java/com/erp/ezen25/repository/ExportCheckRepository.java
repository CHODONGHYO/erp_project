package com.erp.ezen25.repository;

import com.erp.ezen25.entity.ExportCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ExportCheckRepository extends JpaRepository<ExportCheck, Long>, QuerydslPredicateExecutor<ExportCheck> {
}
