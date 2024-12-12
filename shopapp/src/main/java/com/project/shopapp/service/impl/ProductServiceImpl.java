package com.project.shopapp.service.impl;


import com.project.shopapp.dto.ProductDto;
import com.project.shopapp.entity.Category;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.User;
import com.project.shopapp.mapper.CategoryMapper;
import com.project.shopapp.mapper.ProductMapper;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        if (productDto.getProductName()==null||productDto.getProductName().isEmpty()){
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (productDto.getDescription()==null||productDto.getDescription().isEmpty()){
            throw new IllegalArgumentException("Product description cannot be empty");
        }
        if (productDto.getFeaturedImageUrl()==null||productDto.getFeaturedImageUrl().isEmpty()){
            throw new IllegalArgumentException("Featured image cannot be empty");
        }
        if (productDto.getQuantity()<1){
            throw new IllegalArgumentException("Quantity cannot be zero");
        }
        if (productDto.getState()==null){
            productDto.setState(Product.ProductState.AVAILABLE);
        }

        Product product = ProductMapper.maptoEntity(productDto);


        return ProductMapper.maptoDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(Long product_id, ProductDto productDto){
        Product existingproduct = productRepository
                .findById(product_id)
                .orElseThrow(()-> new EntityNotFoundException("Product with id: "+product_id+" not found"));

        if (productDto.getProductName()!= null){
            existingproduct.setProductName(productDto.getProductName());
        }
        if (productDto.getDescription()!= null){
            existingproduct.setDescription(productDto.getDescription());
        }
        if (productDto.getFeaturedImageUrl()!= null){
            existingproduct.setFeaturedImageUrl(productDto.getFeaturedImageUrl());
        }
        if (productDto.getPrice()!= null){
            existingproduct.setPrice(productDto.getPrice());
        }
        if(productDto.getQuantity()!=null){
            existingproduct.setQuantity(productDto.getQuantity());
        }
        if(productDto.getState()!= null){
            existingproduct.setState(productDto.getState());
        }
        if (productDto.isSale() && productDto.getSaleEndDate() != null) {
            if (productDto.getSaleEndDate().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Sale end-date cannot be in the past");
            }
            existingproduct.setSaleEndDate(productDto.getSaleEndDate());
        }


        if (productDto.getSalePrice()!=null){
           if (productDto.getSalePrice().compareTo(existingproduct.getPrice())>=0){
               System.out.println("SalePrice: "+productDto.getSalePrice());
               System.out.println("Price: "+existingproduct.getPrice());
               throw new IllegalArgumentException("Price cannot be greater than or equal to original price");

           }
           existingproduct.setSalePrice(productDto.getSalePrice());
       }

        return ProductMapper.maptoDto(productRepository.save(existingproduct));
    }



    @Override
    public ProductDto getProductById(Long product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(()-> new EntityNotFoundException("Product with id: "+product_id+" not found"));
        return ProductMapper.maptoDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::maptoDto).toList();
    }

    @Override
    public void deleteProduct(Long product_id) {
        productRepository.deleteById(product_id);
    }
}