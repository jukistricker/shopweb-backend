package com.project.shopapp.mapper;

import com.project.shopapp.dto.PurchaseItemDto;
import com.project.shopapp.entity.PurchaseItem;

public class PurchaseItemMapper {
    public static PurchaseItemDto maptoDto(PurchaseItem purchaseItem) {
        if (purchaseItem == null) {
            return null;
        }
        return new PurchaseItemDto(
                purchaseItem.getId(),
                ProductMapper.maptoDto(purchaseItem.getProduct()),
                purchaseItem.getQuantity(),
                VariantAttributeMapper.maptoDto(purchaseItem.getAttribute()),
                PurchaseMapper.maptoDto(purchaseItem.getPurchase()),
                purchaseItem.isRated()
        );
    }
    public static PurchaseItem maptoEntity(PurchaseItemDto purchaseItemDto) {
        if (purchaseItemDto == null) {
            return null;
        }
        return new PurchaseItem(
                purchaseItemDto.getId(),
                ProductMapper.maptoEntity(purchaseItemDto.getProduct()),
                purchaseItemDto.getQuantity(),
                VariantAttributeMapper.maptoEntity(purchaseItemDto.getAttribute()),
                PurchaseMapper.maptoEntity(purchaseItemDto.getPurchase()),
                purchaseItemDto.isRated()
        );
    }
}
