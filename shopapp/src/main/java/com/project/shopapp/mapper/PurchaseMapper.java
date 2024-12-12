package com.project.shopapp.mapper;

import com.project.shopapp.dto.PurchaseDto;
import com.project.shopapp.entity.Purchase;

public class PurchaseMapper {
    public static PurchaseDto maptoDto(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        return new PurchaseDto(
                purchase.getId(),
                OrderMapper.maptoDto(purchase
                        .getOrder()),
                UserMapper.maptoDto(purchase.getUser()),
                PaymentMapper.maptoDto(purchase.getPayment()),
                purchase.getTotalPrice()
        );
    }

    public static Purchase maptoEntity(PurchaseDto purchaseDto) {
        if (purchaseDto == null) {
            return null;
        }
        return new Purchase(
                purchaseDto.getId(),
                OrderMapper.maptoEntity(purchaseDto.getOrder()),
                UserMapper.maptoEntity(purchaseDto.getUser()),
                PaymentMapper.maptoEntity(purchaseDto.getPayment()),
                purchaseDto.getTotalPrice()
        );
    }
}
