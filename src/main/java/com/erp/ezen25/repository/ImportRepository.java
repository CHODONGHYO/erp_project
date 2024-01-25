package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ImportRepository extends JpaRepository<Import, Long>, QuerydslPredicateExecutor<Import> {
    Import findImportByImportId(Long importId);


}
