package com.project.shopapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private Long id;

    @NotNull
    private OrderDto order;   // DTO cho Order

    @NotNull
    private UserDto user;     // DTO cho User

    @NotNull
    private PaymentDto payment;  // DTO cho Payment

    private BigDecimal totalPrice;
}
