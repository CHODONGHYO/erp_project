package com.erp.ezen25.repository;

import com.erp.ezen25.entity.ImportCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ImportCheckRepository extends JpaRepository<ImportCheck, Long>, QuerydslPredicateExecutor<ImportCheck> {
}
