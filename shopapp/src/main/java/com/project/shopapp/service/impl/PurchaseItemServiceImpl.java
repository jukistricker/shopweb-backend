package com.project.shopapp.service.impl;

import com.project.shopapp.dto.PurchaseItemDto;
import com.project.shopapp.entity.Purchase;
import com.project.shopapp.entity.PurchaseItem;
import com.project.shopapp.mapper.PaymentMapper;
import com.project.shopapp.mapper.PurchaseItemMapper;
import com.project.shopapp.mapper.VariantAttributeMapper;
import com.project.shopapp.repository.PurchaseItemRepository;
import com.project.shopapp.repository.PurchaseRepository;
import com.project.shopapp.service.PurchaseItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService {
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemMapper purchaseItemMapper;

    @Override
    public PurchaseItemDto createPurchaseItem(PurchaseItemDto purchaseItemDto){
        if (!purchaseItemDto.isRated()){
            purchaseItemDto.setRated(false);
        }
        PurchaseItem purchaseItem = purchaseItemMapper.maptoEntity(purchaseItemDto);
        return purchaseItemMapper.maptoDto(purchaseItemRepository.save(purchaseItem));
    }

    @Override
    public PurchaseItemDto updatePurchaseItem(Long id,PurchaseItemDto purchaseItemDto){
        PurchaseItem existingPurchaseItem = purchaseItemRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Purchase Item Not Found"));
        if (!existingPurchaseItem.isRated()){
            existingPurchaseItem.setRated(false);
        }
        existingPurchaseItem.setRated(true);
        return purchaseItemMapper.maptoDto(purchaseItemRepository.save(existingPurchaseItem));

    }

    @Override
    public void deletePurchaseItem(Long id){
        purchaseItemRepository.deleteById(id);
    }
    public PurchaseItemDto getPurchaseItemById(Long id){
        PurchaseItem purchaseItem = purchaseItemRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Purchase Item Not Found"));
        return purchaseItemMapper.maptoDto(purchaseItem);
    }

    @Override
    public List<PurchaseItemDto> getPurchaseItemsByPurchaseId(Long purchaseId){
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(()-> new EntityNotFoundException("Purchase Not Found"));
        List<PurchaseItem> purchaseItems = purchaseItemRepository.findByPurchase(purchase);
        return purchaseItems.stream().map(purchaseItemMapper::maptoDto).toList();
    }

}
