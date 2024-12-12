package com.project.shopapp.repository;

import com.project.shopapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
