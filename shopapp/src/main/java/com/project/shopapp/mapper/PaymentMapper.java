package com.project.shopapp.mapper;

import com.project.shopapp.dto.PaymentDto;
import com.project.shopapp.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto maptoDto(Payment payment);
    Payment maptoEntity(PaymentDto paymentDto);
}
