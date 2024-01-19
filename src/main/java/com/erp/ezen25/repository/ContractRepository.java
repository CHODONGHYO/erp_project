package com.erp.ezen25.repository;

import com.erp.ezen25.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAllByOrderByContractDateDesc();
}
