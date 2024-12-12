package com.project.shopapp.mapper;

import com.project.shopapp.dto.CartDto;
import com.project.shopapp.entity.Cart;

public class CartMapper {

    public static CartDto maptoDto(Cart cart) {
        if (cart == null) {
            return null;
        }
        return new CartDto(
                cart.getId(),
                UserMapper.maptoDto(cart.getUser())

        );

    }
    public static Cart maptoEntity(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }
        return new Cart(
                cartDto.getId(),
                UserMapper.maptoEntity(cartDto.getUser())
        );
    }

}
