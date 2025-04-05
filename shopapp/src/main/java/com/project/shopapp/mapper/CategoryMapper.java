package com.project.shopapp.mapper;

import com.project.shopapp.dto.CategoryDto;
import com.project.shopapp.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto maptoDto(Category category);
    Category maptoEntity(CategoryDto dto);
}

