package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Contract;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.ContractRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContractService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    private final ContractRepository contractRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    public List<ContractListResponseDTO> contractList() {
        return contractRepository.findAllByOrderByContractDateDesc().stream()
                .map(ContractListResponseDTO::new)
                .toList();
    }

    public List<ProductBnameListResponseDTO> bnameList() {
        return brandRepository.findAllByOrderByBrandNameAsc().stream()
                .map(ProductBnameListResponseDTO::new)
                .toList();
    }
    public List<ContractPnameListResponseDTO> prodList() {
        return productRepository.findAllByOrderByProductNameAsc().stream()
                .map(ContractPnameListResponseDTO::new)
                .toList();
    }

    public void addContract(ContractAddRequestDTO addRequest, MultipartFile mf) throws IOException {
        addRequest.setContractFile(prodFileUpload(mf, uploadPath));


        Contract contract = addRequest.toEntity();
        contractRepository.save(contract);
    }
    // 파일업로드
    public String prodFileUpload(MultipartFile mf, String uploadPath) throws IOException {
        long fileSize = mf.getSize();
        if (fileSize == 0) {
            return null;
        }
        LocalDate now = LocalDate.now();
        String uuid = UUID.randomUUID().toString();
        String orginMainName = mf.getOriginalFilename();
        String folderpath = makeFolder() + File.separator;
        String saveName = now+ uuid + orginMainName;
        File file = new File(uploadPath+folderpath+saveName);
        mf.transferTo(file);
        return saveName;
    }
    // 날짜 폴더 만들기
    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder ----------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    public void contractDelete (Long contractId) {
        contractRepository.deleteById(contractId);
    }
}
