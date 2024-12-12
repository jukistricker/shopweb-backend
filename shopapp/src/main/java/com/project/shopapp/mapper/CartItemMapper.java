package com.project.shopapp.mapper;

import com.project.shopapp.dto.CartItemDto;
import com.project.shopapp.entity.CartItem;




public class CartItemMapper {
    public static CartItemDto maptoDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        return new CartItemDto(
                cartItem.getId(),
                CartMapper.maptoDto(cartItem.getCart()),
                ProductMapper.maptoDto(cartItem.getProduct()),
                cartItem.getQuantity(),
                ProductVariantMapper.maptoDto(cartItem.getProductVariant())
        );
    }
    public static CartItem maptoEntity(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            return null;
        }
        return new CartItem(
                cartItemDto.getId(),
                CartMapper.maptoEntity(cartItemDto.getCart()),
                ProductMapper.maptoEntity(cartItemDto.getProduct()),
                cartItemDto.getQuantity(),
                ProductVariantMapper.maptoEntity(cartItemDto.getProductVariant())
        );
    }
}
