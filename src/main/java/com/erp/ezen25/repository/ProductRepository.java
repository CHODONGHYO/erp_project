package com.erp.ezen25.repository;

import com.erp.ezen25.queryMapping.MCategoryListMapping;
import com.erp.ezen25.entity.Product_Info;
import com.erp.ezen25.queryMapping.SCategoryListMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product_Info, Long> {

    @Query(value = "select m_category as m_category from product_info group by m_category", nativeQuery = true)
    List<MCategoryListMapping> productInfoGroupByMCategory ();
    @Query(value = "select s_category as s_category from product_info group by s_category", nativeQuery = true)
    List<SCategoryListMapping> productInfoGroupBySCategory ();

    List<Product_Info> findAllByOrderByProductNameAsc ();
}
