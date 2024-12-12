package com.project.shopapp.service;

import com.project.shopapp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long id,CategoryDto categoryDto);
    void deleteCategory(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);

}
