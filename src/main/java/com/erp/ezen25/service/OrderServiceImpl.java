package com.erp.ezen25.service;

import com.erp.ezen25.dto.*;
import com.erp.ezen25.entity.Order;
import com.erp.ezen25.queryMapping.SCategoryListMapping;
import com.erp.ezen25.repository.OrderRepository;
import com.erp.ezen25.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductRepository productRepository;

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
    public OrderDTO findByOrderId(Long orderId){
        Optional<Order> order = repository.findById(orderId);
        return order.map(this::entityToDto).orElse(null);
    }
    @Override
    public void deleteById(Long orderId) {
        repository.deleteById(orderId);
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
    public List<Object[]> joinOrderAndProduct() {
        return repository.joinOrderAndProduct();
    }
    @Override
    public List<String> getSCategoryList(String upperCategoryNo) {
        return repository.getSCategoryList(upperCategoryNo);
    }
    @Override
    public List<String> getMCategoryList() {
        return repository.findMCategoryList();
    }
    @Override
    public List<String> getProductList(String subcategory){
        return repository.findProductList(subcategory);
    }


    /*@Override
    public void remove(Long orderId) {
        repository.deleteByOrderId(orderId);
    }*/
   /* @Override
    public void modify(OrderDTO dto){
        repository.updateByOrderId(dto);
    }*/


}