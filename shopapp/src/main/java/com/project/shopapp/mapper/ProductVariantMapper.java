package com.project.shopapp.mapper;

import com.project.shopapp.dto.ProductVariantDto;
import com.project.shopapp.entity.ProductVariant;

public class ProductVariantMapper {
    public  static ProductVariantDto maptoDto(ProductVariant productVariant){
        if(productVariant == null){
            return null;
        }
        return new ProductVariantDto(
                productVariant.getId(),
                ProductMapper.maptoDto(productVariant.getProduct())
        );
    }
    public static ProductVariant maptoEntity(ProductVariantDto productVariantDto){
        if(productVariantDto == null){
            return null;
        }
        return new ProductVariant(
                productVariantDto.getId(),
                ProductMapper.maptoEntity(productVariantDto.getProduct())
        );
    }
}
