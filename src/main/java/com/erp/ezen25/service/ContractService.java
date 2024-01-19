package com.erp.ezen25.service;

import com.erp.ezen25.dto.ContractListResponseDTO;
import com.erp.ezen25.dto.ProductBnameListResponseDTO;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.ContractRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    public List<ContractListResponseDTO> contractList() {
        return contractRepository.findAll().stream()
                .map(ContractListResponseDTO::new)
                .toList();
    }

    // public List<ProductBnameListResponseDTO>

}
