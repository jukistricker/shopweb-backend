package com.project.shopapp.service;

import com.project.shopapp.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto updatePayment(Long id, PaymentDto paymentDto);
    PaymentDto getPayment(Long id);
    void deletePayment(Long id);
    List<PaymentDto> getPayments();
}
