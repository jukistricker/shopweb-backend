package com.project.shopapp.service.impl;

import com.project.shopapp.dto.CartDto;
import com.project.shopapp.entity.Cart;
import com.project.shopapp.mapper.CartMapper;
import com.project.shopapp.repository.CartRepository;
import com.project.shopapp.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = cartRepository.save(CartMapper.maptoEntity(cartDto));
        return CartMapper.maptoDto(cart);
    }
    @Override
    public List<CartDto> findAll() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(CartMapper::maptoDto).toList();
    }

    @Override
    public CartDto getCartById(Long user_id){
        Cart cart = cartRepository.findByUserId(user_id);

        if(cart == null){
            throw new EntityNotFoundException("Cart not found");
        }

        return CartMapper.maptoDto(cart);
    }



    @Override
    public void deleteCart(Long user_id) {
        Cart cart= cartRepository.findByUserId(user_id);
        if(cart == null){
            throw new EntityNotFoundException("Cart not found");
        }
        cartRepository.delete(cart);
    }
}
