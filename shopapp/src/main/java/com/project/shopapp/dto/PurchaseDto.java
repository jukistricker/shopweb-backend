package com.project.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private Long id;
    private OrderDto order;   // DTO cho Order
    private UserDto user;     // DTO cho User
    private PaymentDto payment;  // DTO cho Payment
    private BigDecimal totalPrice;
}
