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
                ProductVariantMapper.maptoDto(purchaseItem.getVariant()),
                PurchaseMapper.maptoDto(purchaseItem.getPurchase())
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
                ProductVariantMapper.maptoEntity(purchaseItemDto.getVariant()),
                PurchaseMapper.maptoEntity(purchaseItemDto.getPurchase())
        );
    }
}
