package com.project.shopapp.controller;

import com.project.shopapp.dto.CategoryDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/category")
@Validated
@AllArgsConstructor
public class CategoryController {


    private CategoryService categoryService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteCategoryById(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            ResponseMessageDto response = new ResponseMessageDto();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        try {
            List<CategoryDto> categories = categoryService.getAllCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long category_id) {
        try {
            CategoryDto categoryDto = categoryService.getCategoryById(category_id);
            if (categoryDto != null) {
                return new ResponseEntity<>(categoryDto, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            CategoryDto savedCategory = categoryService.createCategory(categoryDto);
            System.out.println("saveCate "+savedCategory);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        }
        catch (Exception e) {
            CategoryDto errorCategory = new CategoryDto();  // Tạo một CategoryDto rỗng hoặc với thông tin lỗi.
            errorCategory.setCate_name("Error creating category");
            errorCategory.setDescription(e.getMessage());

            System.out.println("error: " + e.getMessage());
            return new ResponseEntity<>(errorCategory, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable("id") Long category_id, @Valid @RequestBody CategoryDto categoryDto) {
        try {
            CategoryDto updatedCategory = categoryService.updateCategory(category_id, categoryDto);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        }
        catch (Exception e) {
            CategoryDto errorCategory = new CategoryDto();  // Tạo một CategoryDto rỗng hoặc với thông tin lỗi.
            errorCategory.setCate_name("Error creating category");
            errorCategory.setDescription(e.getMessage());

            System.out.println("error: " + e.getMessage());
            return new ResponseEntity<>(errorCategory, HttpStatus.BAD_REQUEST);

        }

    }








}
