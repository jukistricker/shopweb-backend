package com.project.shopapp.mapper;

import com.project.shopapp.dto.CartItemDto;
import com.project.shopapp.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto maptoDto(CartItem cartItem);

    @Mapping(target = "attribute", source = "variantAttribute")
    CartItem maptoEntity(CartItemDto cartItemDto);

}
