package com.project.shopapp.service.impl;

import com.project.shopapp.dto.PaymentDto;
import com.project.shopapp.entity.Payment;
import com.project.shopapp.mapper.PaymentMapper;
import com.project.shopapp.repository.PaymentRepository;
import com.project.shopapp.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = PaymentMapper.maptoEntity(paymentDto);
        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.maptoDto(savedPayment);
    }

    @Override
    public PaymentDto updatePayment(Long id, PaymentDto paymentDto) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + id));

        existingPayment.setPayName(paymentDto.getPayName());
        Payment updatedPayment = paymentRepository.save(existingPayment);
        return PaymentMapper.maptoDto(updatedPayment);
    }

    @Override
    public PaymentDto getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + id));
        return PaymentMapper.maptoDto(payment);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDto> getPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(PaymentMapper::maptoDto)
                .collect(Collectors.toList());
    }
}