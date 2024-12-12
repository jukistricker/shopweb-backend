package com.project.shopapp.repository;

import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.ProductFeedback;
import com.project.shopapp.entity.PurchaseItem;
import com.project.shopapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, Long> {
    Optional<ProductFeedback> findByUserId(Long userId);
    List<ProductFeedback> findByProduct(Product product);
}
