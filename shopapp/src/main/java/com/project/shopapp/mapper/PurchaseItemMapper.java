package com.project.shopapp.mapper;

import com.project.shopapp.dto.PurchaseItemDto;
import com.project.shopapp.entity.PurchaseItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {
    PurchaseItemDto maptoDto(PurchaseItem purchaseItem);
    PurchaseItem maptoEntity(PurchaseItemDto purchaseItemDto);
}
