package com.project.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemDto {
    private Long id;
    private ProductDto product;       // DTO cho Product
    private int quantity;
    private ProductVariantDto variant; // DTO cho ProductVariant
    private PurchaseDto purchase;     // DTO cho Purchase
}
