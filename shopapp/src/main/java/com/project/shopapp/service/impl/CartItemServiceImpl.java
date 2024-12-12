package com.project.shopapp.service.impl;

import com.project.shopapp.dto.CartItemDto;
import com.project.shopapp.entity.CartItem;
import com.project.shopapp.mapper.CartItemMapper;
import com.project.shopapp.mapper.ProductMapper;
import com.project.shopapp.mapper.ProductVariantMapper;

import com.project.shopapp.repository.CartItemRepository;
import com.project.shopapp.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItemDto createCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = CartItemMapper.maptoEntity(cartItemDto);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return CartItemMapper.maptoDto(savedCartItem);
    }

    @Override
    public CartItemDto updateCartItem(Long id, CartItemDto cartItemDto) {
        CartItem existingCartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with ID: " + id));

        // Update fields
        existingCartItem.setProduct(ProductMapper.maptoEntity(cartItemDto.getProduct()));
        existingCartItem.setQuantity(cartItemDto.getQuantity());
        existingCartItem.setProductVariant(ProductVariantMapper.maptoEntity(cartItemDto.getProductVariant()));

        CartItem updatedCartItem = cartItemRepository.save(existingCartItem);
        return CartItemMapper.maptoDto(updatedCartItem);
    }

    @Override
    public CartItemDto getCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with ID: " + id));
        return CartItemMapper.maptoDto(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new RuntimeException("CartItem not found with ID: " + id);
        }
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItemDto> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems.stream()
                .map(CartItemMapper::maptoDto)
                .collect(Collectors.toList());
    }
}
