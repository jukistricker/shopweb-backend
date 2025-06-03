package com.project.shopapp.service;

import com.project.shopapp.dto.PurchaseDto;
import com.project.shopapp.dto.request.PurchaseRequest;
import com.project.shopapp.entity.PurchaseItem;

import java.util.List;

public interface PurchaseService {
    PurchaseDto createPurchase(PurchaseRequest purchaseRequest);
    PurchaseDto updatePurchase(Long id,PurchaseDto purchaseDto);
    PurchaseDto getPurchase(Long id);
    List<PurchaseDto> getAllPurchasesByUserId(Long userId);
    void deletePurchase(Long id);
}
