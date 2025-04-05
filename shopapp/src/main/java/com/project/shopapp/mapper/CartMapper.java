package com.project.shopapp.mapper;

import com.project.shopapp.dto.CartDto;
import com.project.shopapp.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto maptoDto(Cart cart);

    Cart maptoEntity(CartDto cartDto);
}