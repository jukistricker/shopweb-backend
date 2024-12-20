package com.project.shopapp.controller;

import com.project.shopapp.dto.PurchaseItemDto;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.PurchaseItem;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.service.ProductService;
import com.project.shopapp.service.PurchaseItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("api/v1/item")
public class PurchaseItemController {
    private final ProductRepository productRepository;
    private PurchaseItemService purchaseItemService;
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Validated PurchaseItemDto purchaseItemDto) {
        try {
            PurchaseItemDto newItem = purchaseItemService.createPurchaseItem(purchaseItemDto);
            Product product = productRepository.findById(purchaseItemDto.getProduct().getId()).orElse(null);
            product.setQuantity(product.getQuantity() + newItem.getQuantity());
            productRepository.save(product);
            return  new ResponseEntity<>(newItem, HttpStatus.CREATED);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PurchaseItemDto> update(@PathVariable("id") Long id,PurchaseItemDto purchaseItemDto) {
        try {
            PurchaseItemDto updatedItem = purchaseItemService.updatePurchaseItem(id, purchaseItemDto);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        try {
            purchaseItemService.deletePurchaseItem(id);
            System.out.println("Delete successfully "+id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseItemDto> get(@PathVariable("id") Long id) {
        try {
            PurchaseItemDto purchaseItemDto = purchaseItemService.getPurchaseItemById(id);
            return new ResponseEntity<>(purchaseItemDto, HttpStatus.OK);

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PurchaseItemDto>> getAll(@PathVariable("id") Long id) {
        try {
            List<PurchaseItemDto> purchaseItems = purchaseItemService.getPurchaseItemsByPurchaseId(id);
            return new ResponseEntity<>(purchaseItems, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
