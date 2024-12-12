package com.project.shopapp.controller;

import com.project.shopapp.dto.PurchaseDto;
import com.project.shopapp.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("api/v1/purchase")
public class PurchaseController {
    private PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<PurchaseDto> create(@Valid @RequestBody PurchaseDto purchaseDto) {
        try {
            PurchaseDto createdPurchase = purchaseService.createPurchase(purchaseDto);
            return new ResponseEntity<>(createdPurchase, HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PurchaseDto> update(@Valid @RequestBody PurchaseDto purchaseDto, @PathVariable Long id) {
        try {
            PurchaseDto updatedPurchase = purchaseService.updatePurchase(id, purchaseDto);
            return new ResponseEntity<>(updatedPurchase, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PurchaseDto>> getAll(@PathVariable Long id) {
        try {
            List<PurchaseDto> purchases = purchaseService.getAllPurchasesByUserId(id);
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> getById(@PathVariable Long id) {
        try {
            PurchaseDto purchase = purchaseService.getPurchase(id);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        try {
            purchaseService.deletePurchase(id);
            System.out.println("Purchase deleted"+id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
