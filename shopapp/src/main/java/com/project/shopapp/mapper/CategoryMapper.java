package com.project.shopapp.mapper;

import com.project.shopapp.dto.CategoryDto;
import com.project.shopapp.entity.Category;
import com.project.shopapp.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;

public class CategoryMapper {
    public static CategoryDto maptoDto(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDto(
                category.getId(),
                category.getCate_name(),
                category.getDescription()
        );
    }
    public static Category maptoEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        return
                new Category(
                categoryDto.getId(),
                categoryDto.getCate_name(),
                categoryDto.getDescription()
        );
    }
}
