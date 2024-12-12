package com.project.shopapp.dto;

import com.project.shopapp.entity.Category;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private Long id;

    private UserDto user;

    private String productName;

    private CategoryDto category;

    private String description;

    private String featuredImageUrl;

    private BigDecimal price;

    private Integer quantity;

    private Product.ProductState state;

    private int purchaseCount;

    private float rating;

    private boolean sale;

    private LocalDateTime saleEndDate;

    private BigDecimal salePrice;



}
