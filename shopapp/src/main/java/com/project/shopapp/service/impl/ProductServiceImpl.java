package com.project.shopapp.service.impl;


import com.project.shopapp.dto.ProductDto;
import com.project.shopapp.entity.Category;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.User;
import com.project.shopapp.mapper.ProductMapper;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductDto createProduct(ProductDto productDto) {
        if (productDto.getProductName()==null||productDto.getProductName().isEmpty()){
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (productDto.getDescription()==null||productDto.getDescription().isEmpty()){
            throw new IllegalArgumentException("Product description cannot be empty");
        }
        if (productDto.getFeaturedImageUrl()==null||productDto.getFeaturedImageUrl().isEmpty()){
            productDto.setFeaturedImageUrl("htt://cdn.shopify.com/s/files/1/0559/3713/8775/files/Durable_Chews.jpg?v=1725681866&width=512ps");
        }
        if (productDto.getQuantity()<1){
            throw new IllegalArgumentException("Quantity cannot be zero");
        }
        if (productDto.getState()==null){
            productDto.setState(Product.ProductState.AVAILABLE);
        }

        Product product = productMapper.maptoEntity(productDto);


        return productMapper.maptoDto(productRepository.save(product));
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
        if (productDto.isSale()){
            existingproduct.setSale(true);
            if (productDto.getSalePrice()!=null){
                if (productDto.getSalePrice().compareTo(existingproduct.getPrice())>=0){
                    System.out.println("SalePrice: "+productDto.getSalePrice());
                    System.out.println("Price: "+existingproduct.getPrice());
                    throw new IllegalArgumentException("Price cannot be greater than or equal to original price");

                }
                existingproduct.setSalePrice(productDto.getSalePrice());
            }
            else {
                throw new IllegalArgumentException("Sale price cannot be null");
            }
        }
        else {
            existingproduct.setSale(false);
            existingproduct.setSalePrice(null);
            existingproduct.setSaleEndDate(null);
        }



        System.out.println(productDto);

        return productMapper.maptoDto(productRepository.save(existingproduct));
    }



    @Override
    public ProductDto getProductById(Long product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(()-> new EntityNotFoundException("Product with id: "+product_id+" not found"));
        return productMapper.maptoDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::maptoDto).toList();
    }

    @Override
    public void deleteProduct(Long product_id) {
        productRepository.deleteById(product_id);
    }

    @Override
    public List<ProductDto>  getProductsByUserId(Long user_id){
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new EntityNotFoundException("User with id: "+user_id+" not found"));
        List<Product> products = productRepository.findByUserId(user_id);
        if (products.isEmpty()){
            return null;
        }
        return products.stream().map(productMapper::maptoDto).toList();
    }

    @Override
    public  List<ProductDto> getProductsByCategoryId(Long category_id){
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(()-> new EntityNotFoundException("Category with id: "+category_id+" not found"));
        List<Product> products = productRepository.findByCategoryId(category_id);
        if (products.isEmpty()){
            return null;
        }
        return products.stream().map(productMapper::maptoDto).toList();
    }

    public List<ProductDto> searchProducts(String query) {
        // Kiểm tra chuỗi tìm kiếm có hợp lệ không
        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }

        // Tìm kiếm sản phẩm trong repository
        List<Product> products = productRepository.searchProductsByName(query);


        return products.stream().map(productMapper::maptoDto).toList();
    }

}
