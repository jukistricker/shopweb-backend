package com.project.shopapp.service;

import com.project.shopapp.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long product_id, ProductDto productDto);
    ProductDto getProductById(Long product_id);
    List<ProductDto> getAllProducts();
    void deleteProduct(Long product_id);
}
