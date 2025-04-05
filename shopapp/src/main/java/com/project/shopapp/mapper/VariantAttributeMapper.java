package com.project.shopapp.mapper;

import com.project.shopapp.dto.VariantAttributeDto;
import com.project.shopapp.entity.VariantAttribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariantAttributeMapper {
    VariantAttributeDto maptoDto(VariantAttribute variantAttribute);
    VariantAttribute maptoEntity(VariantAttributeDto variantAttributeDto);
}
