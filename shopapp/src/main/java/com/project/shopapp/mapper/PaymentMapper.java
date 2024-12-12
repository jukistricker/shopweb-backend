package com.project.shopapp.mapper;

import com.project.shopapp.dto.PaymentDto;
import com.project.shopapp.entity.Payment;

public class PaymentMapper {
    public static PaymentDto maptoDto(Payment payment) {
        if (payment == null) {
            return null;
        }
        return new PaymentDto(
                payment.getId(),
                payment.getPayName()
        );
    }
    public static Payment maptoEntity(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }
        return new Payment(
                paymentDto.getId(),
                paymentDto.getPayName()
        );
    }
}
