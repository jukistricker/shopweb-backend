package com.project.shopapp.dto;

import com.project.shopapp.entity.Category;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "User cannot be null")
    private Long userId;

    @NotEmpty(message = "Product name cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9 _-]+$", message = "Product name contains invalid characters")
    private String productName;

    @NotNull(message = "Category cannot be null")
    private Long categoryId;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Featured image URL cannot be empty")
    @Pattern(regexp = "^(http|https)://.*$", message = "Featured image URL must be a valid URL")
    private String featuredImageUrl;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "State cannot be null")
    private Product.ProductState state;

    @Min(value = 0, message = "Purchase count cannot be negative")
    private int purchaseCount;

    @Min(value = 0, message = "Rating cannot be negative")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private float rating;

    private boolean sale;

    @FutureOrPresent(message = "Sale end date must be in the future or present")
    private LocalDateTime saleEndDate;

    @DecimalMin(value = "0.01", message = "Sale price must be greater than 0")
    private BigDecimal salePrice;


}
