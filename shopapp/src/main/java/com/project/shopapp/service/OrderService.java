package com.project.shopapp.service;

import com.project.shopapp.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getOrders();
    OrderDto getOrder(Long id);
    OrderDto updateOrder(Long id,OrderDto orderDto);
    void deleteOrder(Long id);
//    OrderDto createOrder(OrderDto orderDto);
//    OrderDto updateOrder(Long id,OrderDto orderDto);
//    OrderDto getOrder(Long id);
//    List<OrderDto> getOrders();
//    void deleteOrder(Long id);
}
