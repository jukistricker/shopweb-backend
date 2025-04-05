package com.project.shopapp.mapper;

import com.project.shopapp.dto.ProductDto;
import com.project.shopapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "category", target = "category")
    ProductDto maptoDto(Product product);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "category", target = "category")
    Product maptoEntity(ProductDto dto);
}
