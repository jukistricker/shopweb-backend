package com.project.shopapp.service.impl;

import com.project.shopapp.dto.CartItemDto;
import com.project.shopapp.entity.Cart;
import com.project.shopapp.entity.CartItem;
import com.project.shopapp.mapper.*;

import com.project.shopapp.repository.CartItemRepository;
import com.project.shopapp.repository.CartRepository;
import com.project.shopapp.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final VariantAttributeMapper variantAttributeMapper;
    private final CartItemMapper cartItemMapper;
    private final ProductMapper productMapper;

    @Override
    public CartItemDto createCartItem(CartItemDto cartItemDto) {

        CartItem cartItem = cartItemRepository.findByCartAndProductAndAttribute(
                cartMapper.maptoEntity(cartItemDto.getCart()),
                productMapper.maptoEntity(cartItemDto.getProduct()),
                variantAttributeMapper.maptoEntity(cartItemDto.getVariantAttribute())
        );

        if (cartItem != null) {

            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
            CartItem updatedCartItem = cartItemRepository.save(cartItem);
            return cartItemMapper.maptoDto(updatedCartItem);
        } else {

            CartItem newCartItem = cartItemMapper.maptoEntity(cartItemDto);
            CartItem savedCartItem = cartItemRepository.save(newCartItem);
            return cartItemMapper.maptoDto(savedCartItem);
        }
    }



    @Override
    public CartItemDto updateCartItem(Long id, CartItemDto cartItemDto) {
        CartItem existingCartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with ID: " + id));

        existingCartItem.setQuantity(cartItemDto.getQuantity());
        existingCartItem.setAttribute(variantAttributeMapper.maptoEntity(cartItemDto.getVariantAttribute()));


        return cartItemMapper.maptoDto(cartItemRepository.save(existingCartItem));
    }

    @Override
    public CartItemDto getCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with ID: " + id));
        return cartItemMapper.maptoDto(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new RuntimeException("CartItem not found with ID: " + id);
        }
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItemDto> getAllCartItems(Long cart_id) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cart_id));

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream()
                .map(cartItemMapper::maptoDto)
                .collect(Collectors.toList());
    }
}
