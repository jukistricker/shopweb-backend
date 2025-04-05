package com.project.shopapp.controller;


import com.project.shopapp.dto.ProductFeedbackDto;
import com.project.shopapp.service.ProductFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("api/v1/feedback")
public class ProductFeedbackController {
    private ProductFeedbackService productFeedbackService;

    @PostMapping("/create")
    public ResponseEntity<ProductFeedbackDto> create(@Validated @RequestBody ProductFeedbackDto productFeedbackDto) {
        try {
            ProductFeedbackDto createdFeedback = productFeedbackService.createFeedback(productFeedbackDto);
            return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ProductFeedbackDto> update(@PathVariable("id") Long id,ProductFeedbackDto productFeedbackDto) {
        try {
            ProductFeedbackDto updatedFeedback = productFeedbackService.updateFeedback(id, productFeedbackDto);
            return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all/{id}")
    public ResponseEntity<List<ProductFeedbackDto>> getAll(@PathVariable("id") Long id) {
        try {
            List<ProductFeedbackDto> feedbacks = productFeedbackService.getFeedbackByProductId(id);
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        try {
            productFeedbackService.deleteFeedback(id);
            System.out.println("feedback with id "+id+" is deleted");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
