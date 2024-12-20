package com.project.shopapp.service;

import com.project.shopapp.dto.CartItemDto;

import java.util.List;

public interface CartItemService {
    CartItemDto createCartItem(CartItemDto cartItemDto);
    CartItemDto updateCartItem(Long id, CartItemDto cartItemDto);
    CartItemDto getCartItem(Long id);
    void deleteCartItem(Long id);
    List<CartItemDto> getAllCartItems(Long cart_id);
}
