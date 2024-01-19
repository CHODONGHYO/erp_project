package com.erp.ezen25.service;

import com.erp.ezen25.dto.OrderDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;

    /*@Test
    public void testList() {
        PageRequestDTO pageRequestDTO =PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<OrderDTO, Order> resultDTO = orderService.getWithdrawalList(pageRequestDTO);
        for(OrderDTO orderDTO : resultDTO.getDtoList()) {
            System.out.println(orderDTO);
        }
    }*/
}
