package com.project.shopapp.mapper;

import com.project.shopapp.dto.OrderDto;
import com.project.shopapp.entity.Order;

public class OrderMapper {
    public static OrderDto maptoDto(Order order) {
        if (order == null) {
            return null;
        }
        return new OrderDto(
                order.getId(),
                UserMapper.maptoDto(order.getUser()),
                order.getName(),
                order.getPhone(),
                order.getAddress()
        );
    }
    public static Order maptoEntity(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }
        return new Order(
                orderDto.getId(),
                UserMapper.maptoEntity(orderDto.getUser()),
                orderDto.getName(),
                orderDto.getPhone(),
                orderDto.getAddress()
        );
    }
}
