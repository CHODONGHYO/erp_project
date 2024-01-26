package com.erp.ezen25.controller;

import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.repository.MemberRepository;
import com.erp.ezen25.service.ExportService;
import com.erp.ezen25.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ezen25/order")
@Log4j2
@RequiredArgsConstructor
// 발주 관련 Controller
public class OrderController {

    private final OrderService orderService;

    private final ExportService exportService;
    private MemberRepository memberRepository;

    @GetMapping("/list")
    public String orderList(Model model) {
        log.info("발주요청목록 페이지로 이동........");
        List<OrderDTO> orderList = orderService.getList();
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderList";
    }

    // export 관련 Controller
    @GetMapping("/export")
    public String exportListGet() {
        return "redirect:/ezen25/order/export/list";
    }

    @GetMapping("/export/list")
    public void exportList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("requestList. ");
        log.info(pageRequestDTO);

        model.addAttribute("exportResult", exportService.getList(pageRequestDTO));
    }

    @GetMapping("/export/register")
    public void exportRegister(Model model) {
        log.info("GET 형식 Register");

        LocalDateTime LDTCT = LocalDateTime.now();
        LocalDateTime LDTCT3 = LDTCT.plusDays(3);
        // 원하는 형식으로 포맷을 지정합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 형식에 맞게 날짜와 시간을 문자열로 변환합니다.
        String currentTime = LDTCT.format(formatter);
        String currentTimeplus3 = LDTCT3.format(formatter);

        model.addAttribute("Now", currentTime);
        model.addAttribute("Now3", currentTimeplus3);

    }

    @PostMapping("/export/register")
    public String exportRegisterPOST(ExportDTO exportDTO) {
        log.info("POST 형식 Register");

        exportService.register(exportDTO);

        return "redirect:/ezen25/order/export/list";

    }

    @GetMapping({"/export/read", "/export/modify", "/export/print"})
    public void exportRead(@RequestParam("exportId") Long exportId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("Get Read/Modify. exportId : " + exportId);

        ExportDTO e = exportService.read(exportId);

        model.addAttribute("exportDTO", e);
    }

    @PostMapping("/export/remove")
    public String exportRemove(@RequestParam("exportId") Long exportId) {
        log.info("Post Remove. exportId : " + exportId);

        exportService.remove(exportId);

        return "redirect:/ezen25/order/export/list";
    }

    @PostMapping("/export/modify")
    public String exportModify(@ModelAttribute("exportDTO") ExportDTO exportDTO,
                               @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                               RedirectAttributes redirectAttributes) {
        log.info("POST exportModify");

        exportService.modify(exportDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("exportId", exportDTO.getExportId());

        return "redirect:/ezen25/order/export/read";
    }
@GetMapping("/{orderId}")
    public String getOrderDetails(@PathVariable Long orderId, Model model) {
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        // orderDTO가 null이 아닌지 확인하여 모델에 추가
        if (orderDTO != null) {
            model.addAttribute("order", orderDTO);
        } else {
            // orderDTO가 null인 경우 처리 (예: 에러 메시지 표시)
            model.addAttribute("error", "주문을 찾을 수 없습니다");
        }
        // Model에 order 정보를 추가하여 뷰에 전달
        model.addAttribute("order", orderDTO);
        log.info(orderDTO);
        // 상세 정보를 보여줄 뷰 페이지로 이동
        return "ezen25/order/orderDetails";
    }

    @GetMapping("/search")
    public String orderSearch(HttpSession session , Model model, @RequestParam(name = "memberId", required = false) Long memberId) {

        List<OrderDTO> memberorderList = orderService.getListByMemberId(memberId);
        log.info("리스트:"+memberorderList);
        model.addAttribute("orderList", memberorderList);
        return "ezen25/order/orderSearch";

    }

    @GetMapping("/write")
    public String orderWrite(HttpSession session, @RequestParam(value = "userId", required = false) String userId, Model model) {
        log.info("발주서 입력 페이지로 이동..");
        Optional<Member> member = memberRepository.findByUserId(userId);
        if (member.isPresent()) {
            model.addAttribute("member", member.get());
        } else {
            model.addAttribute("member", new Member());
        }
        List<Object[]> polist = orderService.joinOrderAndProduct();
        List<String> mCateList = orderService.getMCategoryList();

        model.addAttribute("mcategories",  mCateList);
        model.addAttribute("member", member);
        log.info("대분류: "+mCateList);
        log.info("멤버:" + member);
        return "ezen25/order/orderWrite";
    }

    @GetMapping("/updatesubcategory")
    @ResponseBody
    public List<String> updateSubCategory(@RequestParam String uppercateno) {
        log.info("갱신완료");

        // uppercateno를 사용하여 소분류 리스트를 가져오는 로직
        List<String> subCategories = orderService.getSCategoryList(uppercateno);
        // 가져온 소분류 리스트 반환
        return subCategories;
    }
    @GetMapping("/getproduct")
    @ResponseBody
    public List<String> getproduct(@RequestParam String subcategory) {
        log.info("갱신완료");

        List<String> products = orderService.getProductList(subcategory);
        // 가져온 소분류 리스트 반환
        log.info("리스트:"+products);
        return products;
    }

    /*@RequestMapping(value = "/{orderId}/delete", method = RequestMethod.POST)
    public String deleteOrder(@PathVariable Long orderId){
        log.info("삭제 컨트롤러 진입");
        orderService.remove(orderId);
        return "redirect:ezen25/order/search";
    }*/
    /*@PostMapping("/deleteSelected")
    @ResponseBody
    public String deleteSelectedOrders(@RequestBody List<Long> orderIds) {
        try {
            orderService.deleteByIds(orderIds);
            // 삭제가 성공하면 "success" 문자열 반환
            return "success";
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지를 반환하거나 적절한 오류 처리를 수행할 수 있습니다.
            e.printStackTrace();
            return "error";
        }
    }*/
    @GetMapping("/delete/{orderId}")
    public String deleteById(@PathVariable Long orderId){
        orderService.deleteById(orderId);
        return "redirect:/order/search";
    }
}
