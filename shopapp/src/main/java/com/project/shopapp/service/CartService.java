package com.project.shopapp.service;

import com.project.shopapp.dto.CartDto;

import java.util.List;

public interface CartService {
    CartDto createCart(CartDto cartDto);
    void deleteCart(Long user_id);
    CartDto getCartById(Long user_id);
    List<CartDto> findAll();
}
