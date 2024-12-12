package com.project.shopapp.service;

import com.project.shopapp.dto.PurchaseItemDto;

import java.util.List;

public interface PurchaseItemService {
    PurchaseItemDto createPurchaseItem(PurchaseItemDto purchaseItemDto);
    PurchaseItemDto updatePurchaseItem(Long id,PurchaseItemDto purchaseItemDto);
    void deletePurchaseItem(Long id);
    PurchaseItemDto getPurchaseItemById(Long id);
    List<PurchaseItemDto> getPurchaseItemsByPurchaseId(Long purchaseId);
}
