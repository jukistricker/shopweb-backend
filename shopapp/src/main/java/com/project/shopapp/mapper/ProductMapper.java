package com.project.shopapp.mapper;

import com.project.shopapp.dto.ProductDto;
import com.project.shopapp.entity.Product;

public class ProductMapper {

    public static ProductDto maptoDto(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDto(
                product.getId(),
                UserMapper.maptoDto(product.getUser()),
                product.getProductName(),
                CategoryMapper.maptoDto(product.getCategory()),
                product.getDescription(),
                product.getFeaturedImageUrl(),
                product.getPrice(),
                product.getQuantity(),
                product.getState(),
                product.getPurchaseCount(),
                product.getRating(),
                product.isSale(),
                product.getSaleEndDate(),
                product.getSalePrice()
        );
    }

    public static Product maptoEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        return new Product(
                productDto.getId(),
                UserMapper.maptoEntity(productDto.getUser()),
                productDto.getProductName(),
                CategoryMapper.maptoEntity(productDto.getCategory()),
                productDto.getDescription(),
                productDto.getFeaturedImageUrl(),
                productDto.getPrice(),
                productDto.getQuantity()
        );
    }
}
