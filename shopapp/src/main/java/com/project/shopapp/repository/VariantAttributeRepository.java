package com.project.shopapp.repository;

import com.project.shopapp.entity.ProductVariant;
import com.project.shopapp.entity.VariantAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantAttributeRepository extends JpaRepository<VariantAttribute, Long> {
    List<VariantAttribute> findByProductVariant(ProductVariant productVariant);
}
