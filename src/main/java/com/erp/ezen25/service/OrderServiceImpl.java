package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.entity.*;
import com.erp.ezen25.queryMapping.SCategoryListMapping;
import com.erp.ezen25.repository.OrderRepository;
import com.erp.ezen25.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import static com.erp.ezen25.dto.OrderDTO.*;



@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Override
    public List<WithdrawalDTO> getWithdrawalList(String orderCode) {
        return repository.getWithdrawalDTO(orderCode);
    }

    @Override
    public List<OrderListDTO> getOrderListDTO() {
        return repository.getOrderListDTO();
    }

    public Long register(OrderDTO orderDTO) {
        Order o = dtoToEntity(orderDTO);
        repository.save(o);
        return o.getOrderId();
    }

    @Override
    public OrderDTO read(Long orderId) {
        Optional<Order> order = repository.findByOrderId(orderId);

        return order.map(this::entityToDto).orElse(null);
    }
    @Override
    public OrderDTO getOrderInfo(String orderCode){
        Optional<Order> order = repository.getByOrderCode(orderCode);

        return order.map(this::entityToDto).orElse(null);
    }
    @Override
    public OrderDTO getOneOrderInfo(String orderCode){
        Optional<Order> order = repository.getOneByOrderCode(orderCode);
        return order.map(this::entityToDto).orElse(null);
    }

    @Override
    public void remove(Long orderId) {
        repository.deleteById(orderId);
    }
    @Override
    @Transactional
    public void listremove(String orderCode){

        repository.deleteByOrderCode(orderCode);
    }
    @Override
    public void modify(OrderDTO orderDTO) {
        Optional<Order> oporder = repository.findById(orderDTO.getOrderId());

        if (oporder.isPresent()) {
            Order order = oporder.get();
            order.changeProduct(Product_Info.builder().productId(orderDTO.getProductId()).build());
            order.changeOrderNum(orderDTO.getOrderNum());
            order.changeOrderDate(orderDTO.getOrderDate());
            order.changeOrderStatus(orderDTO.getOrderStatus());
            order.changeOrderDescription(orderDTO.getOrderDescription());
            repository.save(order);
        }
    }
    @Override
    @Transactional
    public void modifyOrder(String orderCode, String orderDescription) {
        // 주어진 orderCode로 주문을 찾아옵니다.
        List<Order> orders = repository.getListByOrderCode(orderCode);

        // 찾아온 주문들에 대해 orderDescription을 수정하고 저장합니다.
        for (Order order : orders) {
            order.changeOrderDescription(orderDescription);
        }

        repository.saveAll(orders);
    }



    @Override
    public List<OrderDTO> getList() {

        List<Order> result = repository.findAll();

        return result.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<OrderDTO> getmList(String orderCode) {

        List<Order> result = repository.getListByOrderCode(orderCode);

        return result.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getListByMemberId(Long memberId) {

        List<Order> result = repository.getListByMemberId(memberId);

        return result.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getSCategoryList(String upperCategory) {
        return repository.getSCategoryList(upperCategory);
    }

    @Override
    public List<String> getMCategoryList() {
        return repository.findMCategoryList();
    }

    @Override
    public List<String> getProductList(String subcategory) {
        return repository.findProductList(subcategory);
    }
    @Override
    public List<Object[]> getProductListByUpperCategory(String upperCategory) {
        return repository.findProductListByUpCate(upperCategory);
    }

    @Override
    public PageResultDTO<OrderDTO, Order> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("orderId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<Order> result = repository.findAll(booleanBuilder, pageable);

        Function<Order, OrderDTO> fn = (this::entityToDto);

        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QOrder qOrder = QOrder.order;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qOrder.orderId.gt(0L);
        builder.and(expression);

        if (type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if (type.contains("s")) {
            sBuilder.or(qOrder.orderStatus.contains(keyword));
        }

        builder.and(sBuilder);

        return builder;

    }
    @Override
    public long countOrdersWithSpecificDate() {
        String today = LocalDate.now().toString();
/*        log.info("오늘 : " + today);
        String specificDate = "2024-02-01";*/

        return repository.countByOrderDate(today);
    }

}