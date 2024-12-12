package com.project.shopapp.service.impl;

import com.project.shopapp.dto.PurchaseDto;
import com.project.shopapp.entity.Purchase;
import com.project.shopapp.entity.User;
import com.project.shopapp.mapper.PaymentMapper;
import com.project.shopapp.mapper.PurchaseMapper;
import com.project.shopapp.repository.OrderRepository;
import com.project.shopapp.repository.PaymentRepository;
import com.project.shopapp.repository.PurchaseRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.PurchaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private  final PurchaseRepository purchaseRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public PurchaseDto createPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = PurchaseMapper.maptoEntity(purchaseDto);
        return PurchaseMapper.maptoDto(purchaseRepository.save(purchase));
    }

    @Override
    public PurchaseDto updatePurchase(Long id, PurchaseDto purchaseDto) {
        Purchase existPurchase = purchaseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Purchase not found"));

        existPurchase.setPayment(PaymentMapper.maptoEntity(purchaseDto.getPayment()));
        return PurchaseMapper.maptoDto(purchaseRepository.save(existPurchase));
    }

    @Override
    public PurchaseDto getPurchase(Long id){
        Purchase existedPurchase = purchaseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Purchase not found"));
        return PurchaseMapper.maptoDto(existedPurchase);
    }



    @Override
    public List<PurchaseDto> getAllPurchasesByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));

        List<Purchase> purchases = purchaseRepository.findByUser(user);
        return purchases.stream().map(PurchaseMapper::maptoDto).toList();
    }


    @Override
    public void deletePurchase(Long id){
        purchaseRepository.deleteById(id);
    }

}
