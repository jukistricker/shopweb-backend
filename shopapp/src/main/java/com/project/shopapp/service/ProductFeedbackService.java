package com.project.shopapp.service;

import com.project.shopapp.dto.ProductFeedbackDto;

import java.util.List;

public interface ProductFeedbackService {
    ProductFeedbackDto createFeedback(ProductFeedbackDto productFeedbackDto);
    ProductFeedbackDto updateFeedback(Long id,ProductFeedbackDto productFeedbackDto);
    List<ProductFeedbackDto> getFeedbackByProductId(Long id);
    void deleteFeedback(Long id);
}
