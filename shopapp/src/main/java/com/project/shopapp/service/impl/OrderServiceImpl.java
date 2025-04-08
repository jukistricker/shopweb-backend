package com.project.shopapp.service.impl;

import com.project.shopapp.dto.OrderDto;
import com.project.shopapp.entity.Order;
import com.project.shopapp.entity.User;
import com.project.shopapp.mapper.OrderMapper;
import com.project.shopapp.repository.OrderRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.maptoEntity(orderDto);
        return orderMapper.maptoDto(orderRepository.save(order));
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        // Cập nhật các thuộc tính từ DTO sang Entity
        existingOrder.setName(orderDto.getName());
        existingOrder.setPhone(orderDto.getPhone());
        existingOrder.setAddress(orderDto.getAddress());


        return orderMapper.maptoDto(orderRepository.save(existingOrder));
    }

    @Override
    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        return orderMapper.maptoDto(order);
    }

    @Override
    public List<OrderDto> getOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream().map(orderMapper::maptoDto).toList();
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }
}
