package com.project.shopapp.repository;

import com.project.shopapp.entity.Cart;
import com.project.shopapp.entity.CartItem;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.VariantAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    CartItem findByCartAndProductAndAttribute(Cart cart, Product product, VariantAttribute variantAttribute);

}
