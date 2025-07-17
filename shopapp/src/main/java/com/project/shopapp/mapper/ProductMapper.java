package com.project.shopapp.mapper;

import com.project.shopapp.dto.ProductDto;
import com.project.shopapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface ProductMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "categoryId", target = "categoryId")
    ProductDto maptoDto(Product product);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "categoryId", target = "categoryId")
    Product maptoEntity(ProductDto dto);
}

