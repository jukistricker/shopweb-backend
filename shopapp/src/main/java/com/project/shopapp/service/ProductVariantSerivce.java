package com.project.shopapp.service;

import com.project.shopapp.dto.ProductVariantDto;
import com.project.shopapp.dto.VariantAttributeDto;

import java.util.List;

public interface ProductVariantSerivce {
    ProductVariantDto createVariant(ProductVariantDto productVariantDto);

    void deleteVariant(Long id);
    List<ProductVariantDto> getAllVariants(Long id);
    ProductVariantDto getVariant(Long id);

    VariantAttributeDto createAttribute(VariantAttributeDto variantAttributeDto);
    VariantAttributeDto updateAttribute(Long id, VariantAttributeDto variantAttributeDto);
    void deleteAttribute(Long id);
    List<VariantAttributeDto> getAllAttributes(Long id);
    VariantAttributeDto getAttribute(Long id);
}
