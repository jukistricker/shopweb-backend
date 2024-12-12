package com.project.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class VariantAttributeDto {
    private Long id;
    private ProductVariantDto productVariant;
    private String attName;
}
