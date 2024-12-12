package com.project.shopapp.controller;


import com.project.shopapp.dto.ProductDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.entity.Product;
import com.project.shopapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
@Validated
public class ProductController {
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@Validated @RequestBody ProductDto productDto) {
        try {
            System.out.println(productDto);
            ProductDto savedProductDto = productService.createProduct(productDto);
            ResponseMessageDto response = new ResponseMessageDto("create product success", savedProductDto,true);
            return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
           System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessageDto> getAllProducts() {
        try {
            List<ProductDto> productDtoList = productService.getAllProducts();
            ResponseMessageDto response = new ResponseMessageDto("get all product success", productDtoList,true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto("get all product failed", e.getMessage(), false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        try {
            ProductDto productDto = productService.getProductById(id);
            if (productDto != null) {
                ResponseMessageDto response = new ResponseMessageDto("get product success", productDto,true);
                return new ResponseEntity<>(productDto, HttpStatus.OK);
            }
            else {
                System.out.println("product not found");
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProductDto = productService.updateProduct(id, productDto);
            ResponseMessageDto response = new ResponseMessageDto("update product success", updatedProductDto,true);
            return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
        }
        catch (Exception e) {
           System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseMessageDto> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            ResponseMessageDto response = new ResponseMessageDto("delete product success", id,true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto("delete product failed", e.getMessage(), false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
