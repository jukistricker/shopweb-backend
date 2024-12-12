package com.project.shopapp.mapper;

import com.project.shopapp.dto.VariantAttributeDto;
import com.project.shopapp.entity.VariantAttribute;

public class VariantAttributeMapper {
    public static VariantAttributeDto maptoDto(VariantAttribute variantAttribute) {
        if (variantAttribute == null) {
            return null;
        }
        return new VariantAttributeDto(
                variantAttribute.getId(),
                ProductVariantMapper.maptoDto(variantAttribute.getProductVariant()),
                variantAttribute.getAttName()
        );
    }
    public static VariantAttribute maptoEntity(VariantAttributeDto variantAttributeDto) {
        if (variantAttributeDto == null) {
            return null;
        }
        return new VariantAttribute(
                variantAttributeDto.getId(),
                ProductVariantMapper.maptoEntity(variantAttributeDto.getProductVariant()),
                variantAttributeDto.getAttName()
        );
    }
}
