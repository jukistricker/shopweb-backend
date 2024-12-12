package com.project.shopapp.mapper;

import com.project.shopapp.dto.ProductFeedbackDto;
import com.project.shopapp.entity.ProductFeedback;

public class ProductFeedbackMapper {
    public static ProductFeedbackDto maptoDto(ProductFeedback productFeedback) {
        if (productFeedback == null) {
            return null;
        }
        return new ProductFeedbackDto(
                productFeedback.getId(),
                PurchaseItemMapper.maptoDto(productFeedback.getPurchaseItem()),
                ProductMapper.maptoDto(productFeedback.getProduct()),
                UserMapper.maptoDto(productFeedback.getUser()),
                productFeedback.getRate(),
                productFeedback.getFeedback()
        );
    }
    public static ProductFeedback maptoEntity(ProductFeedbackDto productFeedbackDto) {
        if (productFeedbackDto == null) {
            return null;
        }
        return new ProductFeedback(
                productFeedbackDto.getId(),
                PurchaseItemMapper.maptoEntity(productFeedbackDto.getPurchaseItem()),
                ProductMapper.maptoEntity(productFeedbackDto.getProduct()),
                UserMapper.maptoEntity(productFeedbackDto.getUser()),
                productFeedbackDto.getRate(),
                productFeedbackDto.getFeedback()
        );
    }
}
