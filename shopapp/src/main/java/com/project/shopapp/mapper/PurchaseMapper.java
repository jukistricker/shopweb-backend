package com.project.shopapp.mapper;

import com.project.shopapp.dto.PurchaseDto;
import com.project.shopapp.entity.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseDto maptoDto(Purchase purchase);
    Purchase maptoEntity(PurchaseDto dto);
}
