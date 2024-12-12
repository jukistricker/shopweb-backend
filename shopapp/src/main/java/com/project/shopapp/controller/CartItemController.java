package com.project.shopapp.controller;

import com.project.shopapp.dto.CartItemDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cart-item")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<CartItemDto> createCartItem(@Validated @RequestBody CartItemDto cartItemDto) {
        try {
            CartItemDto createdCartItem = cartItemService.createCartItem(cartItemDto);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto(
                    "CartItem created successfully",
                    createdCartItem,
                    true
            );
            return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long id, @Validated @RequestBody CartItemDto cartItemDto) {
        try {
            CartItemDto updatedCartItem = cartItemService.updateCartItem(id, cartItemDto);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto(
                    "CartItem updated successfully",
                    updatedCartItem,
                    true
            );
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItem(@PathVariable Long id) {
        try {
            CartItemDto cartItemDto = cartItemService.getCartItem(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto(
                    "CartItem retrieved successfully",
                    cartItemDto,
                    true
            );
            return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseMessageDto> getAllCartItems() {
        try {
            List<CartItemDto> cartItems = cartItemService.getAllCartItems();
            ResponseMessageDto responseMessageDto = new ResponseMessageDto(
                    "All CartItems retrieved successfully",
                    cartItems,
                    true
            );
            return new ResponseEntity<>(responseMessageDto, HttpStatus.OK);
        } catch (Exception e) {
            ResponseMessageDto responseMessageDto = new ResponseMessageDto(
                    "Failed to retrieve all CartItems",
                    e.getMessage(),
                    false
            );
            return new ResponseEntity<>(responseMessageDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteCartItem(@PathVariable Long id) {
        try {
            cartItemService.deleteCartItem(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto(
                    "CartItem deleted successfully",
                    id,
                    true
            );
            return new ResponseEntity<>(responseMessageDto, HttpStatus.OK);
        } catch (Exception e) {
            ResponseMessageDto responseMessageDto = new ResponseMessageDto(
                    "Failed to delete CartItem",
                    e.getMessage(),
                    false
            );
            return new ResponseEntity<>(responseMessageDto, HttpStatus.BAD_REQUEST);
        }
    }
}
