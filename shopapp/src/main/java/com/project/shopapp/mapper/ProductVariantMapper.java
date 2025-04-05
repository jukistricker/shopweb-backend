package com.project.shopapp.mapper;

import com.project.shopapp.dto.ProductVariantDto;
import com.project.shopapp.entity.ProductVariant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariantDto maptoDto(ProductVariant productVariant);
    ProductVariant maptoEntity(ProductVariantDto productVariantDto);
}
