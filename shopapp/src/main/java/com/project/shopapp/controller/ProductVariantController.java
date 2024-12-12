package com.project.shopapp.controller;


import com.project.shopapp.dto.ProductVariantDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.entity.ProductVariant;
import com.project.shopapp.service.ProductVariantSerivce;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated @AllArgsConstructor
@RequestMapping("api/v1/variant")
public class ProductVariantController {
    private ProductVariantSerivce productVariantService;

    @PostMapping("/create")
    public ResponseEntity<ProductVariantDto> createVariant(@Validated @RequestBody ProductVariantDto productVariantDto) {

        try {
            ProductVariantDto createdProductVariant = productVariantService.createVariant(productVariantDto);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Variant created successfully",createdProductVariant,true);
            return new  ResponseEntity<>(createdProductVariant,HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessageDto> getAllVariants() {
        try {
            List<ProductVariantDto> productVariants = productVariantService.getAllVariants();
            ResponseMessageDto response = new ResponseMessageDto("Variant list retrieved successfully",productVariants,true);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto("Variant retrieval failed",e.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductVariantDto> getVariant(@PathVariable Long id) {
        try {
            ProductVariantDto productVariant = productVariantService.getVariant(id);
            ResponseMessageDto response = new ResponseMessageDto("Variant retrieved successfully",productVariant,true);
            return new ResponseEntity<>(productVariant,HttpStatus.OK);
        }
        catch (Exception e) {
           System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteVariant(@PathVariable Long id) {
        try {
            productVariantService.deleteVariant(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Variant deleted successfully",id,true);
            return new ResponseEntity<>(responseMessageDto,HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto("Variant deletion failed",e.getMessage(),false);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

}