package com.erp.ezen25.service;

import com.erp.ezen25.dto.contractDTOs.*;
import com.erp.ezen25.dto.planDTOs.PlanListResponseDTO;
import com.erp.ezen25.dto.productDTOs.ProductBnameListResponseDTO;
import com.erp.ezen25.entity.Contract;
import com.erp.ezen25.entity.Plan;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.ContractRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
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

    public List<ContractListResponseDTO> getContractListByDate(LocalDate date1, LocalDate date2) {
        List<Contract> cList= null;

        if (date1 == null && date2 == null) {
            cList = contractRepository.findAllByOrderByContractDateDesc();
        } else if (date1 == null) {
            cList = contractRepository.findAllByContractDateLessThanEqualOrderByContractDateDesc(date2);
        } else if (date2 == null) {
            cList = contractRepository.findAllByContractDateGreaterThanEqual(date1);
        } else {
            cList = contractRepository.findAllByContractDateBetween(date1, date2);
        }

        return cList.stream()
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
        String saveName = prodFileUpload(mf, uploadPath);
        addRequest.setContractFile(saveName);

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
        System.out.println(mf.getContentType());
        mf.transferTo(file);
        if (mf.getContentType().equals("application/pdf")) {
            pdfToThumbnail(saveName);
        }
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

    // pdf to image
    private void pdfToThumbnail(String saveName) throws IOException {
        File file = new File(uploadPath+saveName.substring(0,10).replaceAll("-","/")+"/"+saveName);
        PDDocument document = PDDocument.load(file);
        PDFRenderer render = new PDFRenderer(document);
        BufferedImage bim = render.renderImage(0);
        File outputFile = new File(uploadPath+saveName.substring(0,10).replaceAll("-","/")+"/"+saveName.substring(0,saveName.length()-4) + ".jpg");
        ImageIO.write(bim, "jpg", outputFile);
        System.out.println("썸네일만들기 지나감");
    }

    public void contractDelete (Long contractId) {
        contractRepository.deleteById(contractId);
    }

    public ContractDetailResponseDTO contractDetail(Long contractId) {
        return contractRepository.findById(contractId).map(ContractDetailResponseDTO::new).get();
    }

    public void contractUpdate(ContractModifyRequest updateRequest, MultipartFile mf) throws IOException {
        String fileURL = prodFileUpload(mf, uploadPath);
        System.out.println(fileURL);
        System.out.println("!!!!!!!!!!!!");
        if (fileURL != null) {
            updateRequest.setContractFile(fileURL);
        }

        Contract cModify = updateRequest.toEntity();
        contractRepository.save(cModify);
    }
}
