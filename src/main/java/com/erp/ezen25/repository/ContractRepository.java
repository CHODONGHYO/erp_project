package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Contract;
import com.erp.ezen25.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAllByOrderByContractDateDesc();

    List<Contract> findAllByContractDateBetween(LocalDate date1, LocalDate date2);
    List<Contract> findAllByContractDateLessThanEqualOrderByContractDateDesc(LocalDate date);
    List<Contract> findAllByContractDateGreaterThanEqual(LocalDate date);
}
