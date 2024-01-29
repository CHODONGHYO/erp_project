package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
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
    public void remove(Long orderId) {
        repository.deleteById(orderId);
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
    public List<OrderDTO> getList() {

        List<Order> result = repository.findAll();

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
    public List<WithdrawalDTO> getWithdrawalList(String orderCode) {

        List<Order> result = repository.findByOrderCode(orderCode); /*발주요청리스트에서 보낼 orderCode*/

        return result.stream()
                .map(this::entityToDtoForWithdrawal)
                .collect(Collectors.toList());
    }

    @Override
    public String getNameByOrderCode(String orderCode) {
        List<Order> result = repository.findByOrderCode(orderCode);
        System.out.println("result:" + result);
        if (!result.isEmpty()) {
            return result.get(0).getMember().getName();
        } else {
            // Handle the case where the list is empty
            log.error("No order found for orderCode: {}", orderCode);
            return "";
        }
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


}