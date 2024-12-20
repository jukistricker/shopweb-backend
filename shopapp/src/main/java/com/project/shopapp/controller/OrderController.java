package com.project.shopapp.controller;


import com.project.shopapp.dto.CartDto;
import com.project.shopapp.dto.OrderDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.entity.Order;
import com.project.shopapp.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@Validated @AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@Validated @RequestBody OrderDto orderDto) {
       try {
           OrderDto createdcorder = orderService.createOrder(orderDto);
           ResponseMessageDto responseMessageDto = new ResponseMessageDto("create order successfully",createdcorder, true);
           return new ResponseEntity<>(createdcorder, HttpStatus.CREATED);
       }
       catch (Exception e) {
           System.out.println(e.getMessage());
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id,@RequestBody OrderDto orderDto) {
        try{
            OrderDto updatedcorder = orderService.updateOrder(id, orderDto);
            ResponseMessageDto response = new ResponseMessageDto("update order successfully",updatedcorder, true);
            return new ResponseEntity<>(updatedcorder, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable Long id) {
        try {
            List<OrderDto> orders = orderService.getOrders(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("get orders successfully",orders, true);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("get orders failed",e.getMessage(), false);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        try {
            OrderDto order = orderService.getOrder(id);
            ResponseMessageDto response = new ResponseMessageDto("get order successfully",order, true);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("get order failed",e.getMessage(), false);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("delete order successfully",id, true);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("delete order failed",e.getMessage(), false);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.BAD_REQUEST);
        }

    }

}
