package com.erp.ezen25.controller;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.dto.planDTOs.PlanListResponseDTO;
import com.erp.ezen25.dto.productDTOs.ProductListResponseDTO;
import com.erp.ezen25.dto.productDTOs.ProductMCateListResponseDTO;
import com.erp.ezen25.dto.productDTOs.ProductSCateListResponseDTO;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.repository.MemberRepository;
import com.erp.ezen25.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final ExportService exportService;

    @Autowired
    private final PlanService planService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final MemberService memberService;
    @GetMapping("/list")
    public String orderList(Model model) {
        log.info("발주요청목록 페이지로 이동........");
        List<OrderDTO> orderList = orderService.getList();
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderList";
    }
    @GetMapping("/plancheck")
    public String planList(Model model){
        log.info("계획확인");
        List<PlanListResponseDTO> planlist = planService.getPlanList();

        model.addAttribute("planist", planlist);
        return "ezen25/order/planlist";
    }

    @GetMapping("/rd")
    public String getOrderDetails(@RequestParam("orderId") Long orderId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        OrderDTO orderdto = orderService.read(orderId);
        model.addAttribute("orderDTO", orderdto);
        return "ezen25/order/orderDetails";
    }
    @GetMapping("/print")
    public String orderprint(@RequestParam("orderId") Long orderId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model){
        OrderDTO orderdto = orderService.read(orderId);
        model.addAttribute("orderDTO", orderdto);
        return "ezen25/order/orderPrint";
    }
    @GetMapping("/mod")
    public String orderMod(@RequestParam("orderId") Long orderId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model){
        OrderDTO orderdto = orderService.read(orderId);
        model.addAttribute("orderDTO", orderdto);
        return "ezen25/order/orderModify";
    }
    @PostMapping("/mod")
    public String orderMod(@ModelAttribute("orderDTO") OrderDTO orderDTO,
                               @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                               RedirectAttributes redirectAttributes) {

        log.info("진입");
        orderService.modify(orderDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("orderId", orderDTO.getOrderId());

        return "redirect:/ezen25/order/rd";
    }
    /*@GetMapping("/search")
    public String orderSearch(HttpSession session , Model model, @RequestParam(name = "memberId", required = false) Long memberId,@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO) {

//        List<OrderDTO> memberorderList = orderService.getListByMemberId(memberId);
//        log.info("리스트:"+memberorderList);
//        model.addAttribute("orderList", memberorderList);
        List<OrderDTO> orderList = orderService.getList();
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderSearch";

    }*/
    @GetMapping("/search2")
    public String orderSearchByCode( Model model,@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO) {

//        List<OrderDTO> memberorderList = orderService.getListByMemberId(memberId);
//        log.info("리스트:"+memberorderList);
//        model.addAttribute("orderList", memberorderList);
        List<OrderDTO> orderList = orderService.getList();
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderSearch2";

    }
    /* @GetMapping("/write")
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
         return "/ezen25/order/orderRegister";
     }*/
    @GetMapping("/register")
    public String register(Model model) {
        log.info("GET 형식 Register");


        List<String> mCateList = orderService.getMCategoryList();


        LocalDateTime LDTCT = LocalDateTime.now();
        LocalDateTime LDTCT3 = LDTCT.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentTime = LDTCT.format(formatter);
        String currentTimeplus3 = LDTCT3.format(formatter);
        model.addAttribute("mcategories", mCateList);

        model.addAttribute("Now", currentTime);
        model.addAttribute("Now3", currentTimeplus3);

        return "ezen25/order/orderRegister";
    }

    @PostMapping("/register")
    public String orderRegister(OrderDTO orderDTO) {
        log.info("POST 형식");
        orderService.register(orderDTO);


        return "redirect:/ezen25/order/search";

    }
    @GetMapping("/getSubcategories")
    @ResponseBody
    public List<String> getSubcategories(@RequestParam("upperCategory") String upperCategory) {
        List<String> subcategories = orderService.getSCategoryList(upperCategory);
        return subcategories;
    }

    @GetMapping("/getproducts")
    @ResponseBody
    public List<String> getProducts(@RequestParam("subcategory") String subcategory) {
        return orderService.getProductList(subcategory);
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

    @PostMapping("/del")
    public String orderRm(@RequestParam(value="orderId",required = false) Long orderId) {
        log.info("제거 : " + orderId);

        orderService.remove(orderId);

        return "redirect:/ezen25/order/search";
    }
    @GetMapping("search")
    public String listbymember(Model model, HttpSession session,@RequestParam(value="orderCode",required = false) String orderCode){
        log.info("orderCode는 :" + orderCode);
        List<OrderDTO> orderList = orderService.getmList(orderCode);

        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderSearch";
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


}
