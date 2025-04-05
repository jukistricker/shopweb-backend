package com.project.shopapp.mapper;

import com.project.shopapp.dto.ProductFeedbackDto;
import com.project.shopapp.entity.ProductFeedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductFeedbackMapper {
    ProductFeedbackDto maptoDto(ProductFeedback productFeedback);
    ProductFeedback maptoEntity(ProductFeedbackDto productFeedbackDto);
}
