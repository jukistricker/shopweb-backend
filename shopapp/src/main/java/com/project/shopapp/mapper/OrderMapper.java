package com.project.shopapp.mapper;

import com.project.shopapp.dto.OrderDto;
import com.project.shopapp.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto maptoDto(Order order);
    Order maptoEntity(OrderDto orderDto);
}
