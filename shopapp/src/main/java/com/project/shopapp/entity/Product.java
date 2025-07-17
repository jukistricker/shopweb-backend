package com.project.shopapp.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "user_id",length = 100,nullable = false)
    private Long userId;

    @Column(name = "product_name",length = 100,nullable = false)
    private String productName;


    @Column(name = "category_id",length = 100,nullable = false)
    private Long categoryId;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "featured_image_url",nullable = false)
    private String featuredImageUrl;

    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "state")
    @Enumerated(EnumType.STRING) // Lưu giá trị Enum dưới dạng chuỗi trong DB
    private ProductState state;

    @Column(name = "purchase_count")
    private int purchaseCount;

    @Column(name = "rating")
    private float rating;

    @Column(name = "sale")
    private boolean sale=false;

    @Column(name = "sale_end_date")
    private LocalDateTime saleEndDate;

    @Column(name = "sale_price", precision = 18, scale = 2)
    private BigDecimal salePrice;

    public enum ProductState {
        AVAILABLE,
        OUT_OF_STOCK,
        PRE_ORDER
    }

    public Product(Long id, Long userId, String productName,Long categoryId,String description,String featuredImageUrl, BigDecimal price,Integer quantity ){
        this.id = id;
        this.userId = userId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.featuredImageUrl = featuredImageUrl;
        this.price = price;
        this.quantity = quantity;

    }

}

