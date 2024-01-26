package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Import;
import com.erp.ezen25.entity.ImportCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ImportCheckRepository extends JpaRepository<ImportCheck, Long>, QuerydslPredicateExecutor<ImportCheck> {
    ImportCheck findImportCheckByImportCheckId(Long importCheckId);

    ImportCheck findImportCheckByImportId(Import importId);
    Optional<ImportCheck> findByImportId(Import importId);


}
