package com.project.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private CartDto cart;
    private ProductDto product;  // DTO cho sản phẩm
    private int quantity;
    private ProductVariantDto productVariant;  // DTO cho sản phẩm variant
}
