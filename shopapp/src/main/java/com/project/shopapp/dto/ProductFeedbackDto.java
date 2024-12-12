package com.project.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFeedbackDto {
    private Long id;
    private PurchaseItemDto purchaseItem;  // DTO cho Product
    private ProductDto product;
    private UserDto user;        // DTO cho User
    private int rate;
    private String feedback;
}
