package com.project.shopapp.service.impl;


import com.project.shopapp.dto.CategoryDto;
import com.project.shopapp.entity.Category;
import com.project.shopapp.mapper.CategoryMapper;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto){
        System.out.println("categoryDto "+categoryDto);
        Category category = CategoryMapper.maptoEntity(categoryDto);
        System.out.println("category "+category);
        return CategoryMapper.maptoDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto){
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("category not found"));
        existingCategory.setCate_name(categoryDto.getCate_name());
        existingCategory.setDescription(categoryDto.getDescription());
        return CategoryMapper.maptoDto(categoryRepository.save(existingCategory));
    }

    @Override
    public CategoryDto getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("category not found"));
        return CategoryMapper.maptoDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::maptoDto).toList();
    }

    @Override
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }


}
