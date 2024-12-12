package com.project.shopapp.service.impl;

import com.project.shopapp.dto.OrderDto;
import com.project.shopapp.entity.Order;
import com.project.shopapp.mapper.OrderMapper;
import com.project.shopapp.repository.OrderRepository;
import com.project.shopapp.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapper.maptoEntity(orderDto);
        return OrderMapper.maptoDto(orderRepository.save(order));
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        // Cập nhật các thuộc tính từ DTO sang Entity
        existingOrder.setAddress(orderDto.getAddress());


        return OrderMapper.maptoDto(orderRepository.save(existingOrder));
    }

    @Override
    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        return OrderMapper.maptoDto(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::maptoDto).toList();
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }
}
