package com.project.shopapp.service;

import com.project.shopapp.dto.PurchaseDto;

import java.util.List;

public interface PurchaseService {
    PurchaseDto createPurchase(PurchaseDto purchaseDto);
    PurchaseDto updatePurchase(Long id,PurchaseDto purchaseDto);
    PurchaseDto getPurchase(Long id);
    List<PurchaseDto> getAllPurchasesByUserId(Long userId);
    void deletePurchase(Long id);
}
