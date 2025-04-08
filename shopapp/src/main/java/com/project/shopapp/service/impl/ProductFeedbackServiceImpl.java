package com.project.shopapp.service.impl;


import com.project.shopapp.dto.ProductFeedbackDto;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.ProductFeedback;
import com.project.shopapp.entity.PurchaseItem;
import com.project.shopapp.mapper.ProductFeedbackMapper;
import com.project.shopapp.mapper.ProductMapper;
import com.project.shopapp.repository.ProductFeedbackRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.repository.PurchaseItemRepository;
import com.project.shopapp.service.ProductFeedbackService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductFeedbackServiceImpl implements ProductFeedbackService {

    private  final ProductFeedbackRepository productFeedbackRepository;
    private final ProductRepository productRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final ProductFeedbackMapper productFeedbackMapper;
    private final ProductMapper productMapper;

    @Override
    public ProductFeedbackDto createFeedback(ProductFeedbackDto productFeedbackDto){
        if (productFeedbackDto.getProduct()==null){
            PurchaseItem purchaseItem = purchaseItemRepository.findById(productFeedbackDto.getPurchaseItem().getId())
                            .orElseThrow(()-> new EntityNotFoundException("Item not found"));
            Product product = productRepository.findById(purchaseItem.getProduct().getId())
                    .orElseThrow(()-> new EntityNotFoundException("Product not found"));
            productFeedbackDto.setProduct(productMapper.maptoDto(product));
        }
        ProductFeedback newProductFeedback = productFeedbackMapper.maptoEntity(productFeedbackDto);

        return productFeedbackMapper.maptoDto(productFeedbackRepository.save(newProductFeedback));
    }

    @Override
    public  ProductFeedbackDto updateFeedback(Long id,ProductFeedbackDto productFeedbackDto){
        ProductFeedback existingFeedback = productFeedbackRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("feedback not found"));
        existingFeedback.setRate(productFeedbackDto.getRate());
        existingFeedback.setFeedback(productFeedbackDto.getFeedback());
        return productFeedbackMapper.maptoDto(productFeedbackRepository.save(existingFeedback));
    }

    @Override
    public List<ProductFeedbackDto> getFeedbackByProductId(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("product not found"));
        List<ProductFeedback> productFeedbacks = productFeedbackRepository.findByProduct(product).stream().toList();
        return productFeedbacks.stream().map(productFeedbackMapper::maptoDto).toList();

    }


    @Override
    public void deleteFeedback(Long id){
        productFeedbackRepository.deleteById(id);
    }




}
