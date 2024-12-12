package com.project.shopapp.controller;


import com.project.shopapp.dto.CartDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@Validated
@AllArgsConstructor
public class CartController {
    private CartService cartService;
    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart(@Validated @RequestBody CartDto cartDto) {
        try {
            CartDto createdCart = cartService.createCart(cartDto);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("create cart", createdCart, true);
            return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessageDto> getAll() {
        try {
            List<CartDto> carts = cartService.findAll();
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("get all carts", carts, true);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.OK);

        }
        catch (Exception e) {
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("get all carts", e.getMessage(), false);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") Long id) {
        try {
            CartDto cartDto = cartService.getCartById(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("get cart by id", cartDto, true);
            return new ResponseEntity<>(cartDto, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteCart(@PathVariable Long id) {
        try {
            cartService.deleteCart(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("delete cart success", id, true);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("delete cart error", e.getMessage(), false);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.BAD_REQUEST);
        }
    }

}
