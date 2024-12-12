package com.project.shopapp.repository;

import com.project.shopapp.entity.Purchase;
import com.project.shopapp.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
    List<PurchaseItem> findByPurchase(Purchase purchase);
}
