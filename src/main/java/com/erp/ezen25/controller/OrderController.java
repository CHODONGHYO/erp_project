package com.erp.ezen25.controller;

import com.erp.ezen25.dto.ExportDTO;
import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.OrderListDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.service.ExportService;
import com.erp.ezen25.service.ImportService;
import com.erp.ezen25.service.OrderService;
import com.erp.ezen25.service.ProductService;
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
import org.aspectj.weaver.ast.Or;
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

    private final OrderService orderService;

    private final ExportService exportService;

    private final ImportService importService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("/list")
    public String orderList(Model model) {
        log.info("발주요청목록 페이지로 이동........");
        List<OrderListDTO> orderList = orderService.getOrderListDTO();
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderList";
    }

    @GetMapping("/rd")
    public String getOrderDetails(@RequestParam("orderId") Long orderId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        OrderDTO orderdto = orderService.read(orderId);
        model.addAttribute("orderDTO", orderdto);
        return "ezen25/order/orderDetails";
    }
    @GetMapping("/print")
    public String orderprint(@RequestParam("orderCode") String orderCode, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model){
        List<OrderDTO> orderList = orderService.getmList(orderCode);
        OrderDTO orderdto = orderService.getOneOrderInfo(orderCode);
        model.addAttribute("orderlist", orderList);
        model.addAttribute("orderdto",orderdto);
        log.info("print로가는 orderdto는:"+orderdto);
        model.addAttribute("orderCode",orderCode);
        return "ezen25/order/orderPrint";
    }
    @GetMapping("/mod")
    public String itemMod(@RequestParam("orderId") Long orderId, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model){
        OrderDTO orderdto = orderService.read(orderId);
        model.addAttribute("orderDTO", orderdto);
        return "ezen25/order/itemModify";
    }
    @PostMapping("/mod")
    public String itemMod(@ModelAttribute("orderDTO") OrderDTO orderDTO,
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
    @GetMapping("/ordermod")
    public String orderMod(@RequestParam("orderCode") String orderCode, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model){
        log.info("받은 수정진입 orderCode--------------:" +orderCode);
        List<OrderDTO> orderList = orderService.getmList(orderCode);

        OrderDTO orderdto = orderService.getOneOrderInfo(orderCode);
        model.addAttribute("orderlist", orderList);
        model.addAttribute("orderdto",orderdto);
        model.addAttribute("orderCode",orderCode);
        return "ezen25/order/orderModify";
    }
    @PostMapping("/ordermod")
    public String orderMod(@RequestParam("orderCode") String orderCode,@RequestParam("orderDescription") String orderDescription,@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                           RedirectAttributes redirectAttributes) {

        log.info("진입");
        orderService.modifyOrder(orderCode, orderDescription);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/ezen25/order/search";
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
    @GetMapping("/search")
    public String orderSearchByCode(HttpSession session,Model model,@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO) {

//        List<OrderDTO> memberorderList = orderService.getListByMemberId(memberId);
//        log.info("리스트:"+memberorderList);
//        model.addAttribute("orderList", memberorderList);
        List<OrderDTO> orderList = orderService.getList();
        model.addAttribute("orderList", orderList);
        return "ezen25/order/orderHistory";

    }
    @GetMapping("/itemlist")
    public String listbymember(Model model, HttpSession session,@RequestParam(value="orderCode",required = false) String orderCode,
                               @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO){
        log.info("itemlist진입");
        log.info("orderCode는 :" + orderCode);
        List<OrderDTO> orderList = orderService.getmList(orderCode);
        model.addAttribute("orderList", orderList);
        log.info("리스트는:"+orderList);
        model.addAttribute("orderCode",orderCode);
        log.info("보내는 orderCode:"+orderCode);
        return "ezen25/order/itemList";
    }

    @GetMapping("/register")
    public String register(Model model,@RequestParam(value="orderCode",required = false) String orderCode) {
        log.info("GET 형식 Register");


        List<String> mCateList = orderService.getMCategoryList();
        List<MemberDTO> mList = memberService.getAllMembers();
        List<OrderDTO> oList = orderService.getmList(orderCode);

        LocalDateTime LDTCT = LocalDateTime.now();
        LocalDateTime LDTCT3 = LDTCT.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentTime = LDTCT.format(formatter);
        String currentTimeplus3 = LDTCT3.format(formatter);
        model.addAttribute("mcategories", mCateList);
        model.addAttribute("mList", mList);
        model.addAttribute("Now", currentTime);
        model.addAttribute("Now3", currentTimeplus3);
        model.addAttribute("list","oList");
        return "ezen25/order/orderRegister";
    }

    @PostMapping("/register")
    public String orderRegister(OrderDTO orderDTO) {
        log.info("POST 형식");
        orderService.register(orderDTO);


        return "redirect:/ezen25/order/search";

    }
    @GetMapping("/itemregister")
    public String addItems(Model model,@RequestParam(value="orderCode",required = false) String orderCode){
        List<String> mCateList = orderService.getMCategoryList();
        List<MemberDTO> mList = memberService.getAllMembers();

        OrderDTO oList = orderService.getOneOrderInfo(orderCode);
        log.info("발주코드에따른 정보는:"+oList);

        LocalDateTime LDTCT = LocalDateTime.now();
        LocalDateTime LDTCT3 = LDTCT.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentTime = LDTCT.format(formatter);
        String currentTimeplus3 = LDTCT3.format(formatter);
        model.addAttribute("mcategories", mCateList);
        model.addAttribute("mList", mList);
        model.addAttribute("Now", currentTime);
        model.addAttribute("Now3", currentTimeplus3);
        model.addAttribute("olist",oList);

        return "ezen25/order/orderItemRegister";
    }
    @PostMapping("itemregister")
    public String itmRegister(OrderDTO orderDTO) {
        log.info("POST 형식");
        orderService.register(orderDTO);


        return "redirect:/ezen25/order/itemList";

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

    @PostMapping("/itemdel")
    public String orderRm(@RequestParam(value="orderId",required = false) Long orderId) {
        log.info("제거 : " + orderId);

        orderService.remove(orderId);

        return "redirect:/ezen25/order/search";
    }
    @PostMapping("/orderdel")
    public String orderRm(@RequestParam(value="orderCode",required = false) String orderCode) {
        log.info("받은 삭제 orderCode--------------:" +orderCode);

        orderService.listremove(orderCode);

        return "redirect:/ezen25/order/search";
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
