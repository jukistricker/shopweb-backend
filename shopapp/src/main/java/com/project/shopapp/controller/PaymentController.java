package com.project.shopapp.controller;

import com.project.shopapp.dto.PaymentDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@Validated
@AllArgsConstructor

public class PaymentController {
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@Validated @RequestBody PaymentDto paymentDto) {
        try {
            PaymentDto createdPayment = paymentService.createPayment(paymentDto);
             return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
             return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@Validated @PathVariable Long id, @RequestBody PaymentDto paymentDto) {
        try {
            PaymentDto updatedPayment = paymentService.updatePayment(id, paymentDto);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
        try {
            PaymentDto paymentDto = paymentService.getPayment(id);
//            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Get payment successfully", paymentDto, true);
            return new ResponseEntity<>(paymentDto, HttpStatus.OK);
        } catch (Exception e) {
           System.out.println(e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        try {
            List<PaymentDto> allPayments = paymentService.getPayments();
//            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Get all payments successfully", allPayments, true);
            return new ResponseEntity<>(allPayments, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Get all payments failed", e.getMessage(), false);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ResponseMessageDto> deletePayment(@PathVariable Long id) {
    try {
      paymentService.deletePayment(id);
      //            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Delete payment
      // successfully", id, true);
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch (Exception e) {
      //            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Delete payment
      // failed", e.getMessage(), false);
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    }
}