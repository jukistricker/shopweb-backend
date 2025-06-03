package com.project.shopapp.service.impl;

import com.project.shopapp.dto.PurchaseDto;
import com.project.shopapp.dto.request.PurchaseRequest;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.Purchase;
import com.project.shopapp.entity.PurchaseItem;
import com.project.shopapp.entity.User;
import com.project.shopapp.mapper.PaymentMapper;
import com.project.shopapp.mapper.PurchaseMapper;
import com.project.shopapp.repository.*;
import com.project.shopapp.service.ProductService;
import com.project.shopapp.service.PurchaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private  final PurchaseRepository purchaseRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PurchaseMapper purchaseMapper;
    private final PaymentMapper paymentMapper;
    private final ProductRepository productRepository;
    private final PurchaseItemRepository purchaseItemRepository;


    @Transactional()
    @Override
    public PurchaseDto createPurchase(PurchaseRequest purchaseRequest) {

        if(purchaseRequest.getItems().isEmpty()){
            throw new IllegalArgumentException("Purchase items must have at least one item");
        }
        if(!orderRepository.existsById(purchaseRequest.getPurchase().getOrder().getId())){
            throw new EntityNotFoundException("Order not found");
        }

        if(!userRepository.existsById(purchaseRequest.getPurchase().getUser().getId())){
            throw new EntityNotFoundException("User not found");
        }

        Purchase purchase = purchaseMapper.maptoEntity(purchaseRequest.getPurchase());
        purchase.setTotalPrice(BigDecimal.ZERO);


        for (PurchaseItem item : purchaseRequest.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(EntityNotFoundException::new);
            if(item.getQuantity()>product.getQuantity()){
                throw new IllegalArgumentException("Quantity is greater than product quantity");
            }
            item.setPurchase(purchase);
            item.setProduct(product);
            product.setQuantity(product.getQuantity()-item.getQuantity());
            item.setRated(false);
            item.setAttribute(item.getAttribute());

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            purchase.setTotalPrice(purchase.getTotalPrice().add(itemTotal));
            productRepository.save(product);
            purchaseItemRepository.save(item);
        }
        purchaseRepository.save(purchase);

    return purchaseMapper.maptoDto(purchase);
    }

    @Override
    public PurchaseDto updatePurchase(Long id, PurchaseDto purchaseDto) {
        Purchase existPurchase = purchaseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Purchase not found"));

        existPurchase.setPayment(paymentMapper.maptoEntity(purchaseDto.getPayment()));
        return purchaseMapper.maptoDto(purchaseRepository.save(existPurchase));
    }

    @Override
    public PurchaseDto getPurchase(Long id){
        Purchase existedPurchase = purchaseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Purchase not found"));
        return purchaseMapper.maptoDto(existedPurchase);
    }



    @Override
    public List<PurchaseDto> getAllPurchasesByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));

        List<Purchase> purchases = purchaseRepository.findByUser(user);
        return purchases.stream().map(purchaseMapper::maptoDto).toList();
    }


    @Override
    public void deletePurchase(Long id){
        purchaseRepository.deleteById(id);
    }

}
