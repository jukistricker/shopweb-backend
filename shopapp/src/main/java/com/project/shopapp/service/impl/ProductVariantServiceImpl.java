package com.project.shopapp.service.impl;

import com.project.shopapp.dto.ProductVariantDto;
import com.project.shopapp.dto.VariantAttributeDto;
import com.project.shopapp.entity.Product;
import com.project.shopapp.entity.ProductVariant;
import com.project.shopapp.entity.VariantAttribute;
import com.project.shopapp.mapper.ProductVariantMapper;
import com.project.shopapp.mapper.VariantAttributeMapper;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.repository.ProductVariantRepository;
import com.project.shopapp.repository.VariantAttributeRepository;
import com.project.shopapp.service.ProductVariantSerivce;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantSerivce {

    private final ProductVariantRepository productVariantRepository;
    private final VariantAttributeRepository variantAttributeRepository;
    private final ProductRepository productRepository;
    private final ProductVariantMapper productVariantMapper;
    private final VariantAttributeMapper variantAttributeMapper;

    @Override
    public ProductVariantDto createVariant(ProductVariantDto productVariantDto) {
        ProductVariant newProductVariant = productVariantMapper.maptoEntity(productVariantDto);
        return productVariantMapper.maptoDto(productVariantRepository.save(newProductVariant));
    }

    @Override
    public ProductVariantDto getVariant(Long id) {
        ProductVariant productVariant = productVariantRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product variant not found with id: " + id));
        return productVariantMapper.maptoDto(productVariant);
    }

    @Override
    public List<ProductVariantDto> getAllVariants(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        List<ProductVariant> productVariants = productVariantRepository.findByProduct(product);
        if (productVariants.isEmpty()) {
            return null;
        }
        return productVariants.stream().map(productVariantMapper::maptoDto).toList();
    }

    @Override
    public void deleteVariant(Long id) {
        if (!productVariantRepository.existsById(id)) {
            throw new EntityNotFoundException("Product variant not found with id: " + id);
        }
        productVariantRepository.deleteById(id);
    }

    @Override
    public VariantAttributeDto getAttribute(Long id) {
        VariantAttribute variantAttribute = variantAttributeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variant attribute not found with id: " + id));
        return variantAttributeMapper.maptoDto(variantAttribute);

    }

    @Override
    public VariantAttributeDto createAttribute(VariantAttributeDto variantAttributeDto) {
        VariantAttribute newVariantAttribute = variantAttributeMapper.maptoEntity(variantAttributeDto);
        return variantAttributeMapper.maptoDto(variantAttributeRepository.save(newVariantAttribute));
    }

    @Override
    public List<VariantAttributeDto> getAllAttributes(Long id) {

        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product variant not found with id: " + id));
        List<VariantAttribute> variantAttributes = variantAttributeRepository
                .findByProductVariant(productVariant);
        System.out.println(variantAttributes);
        return variantAttributes.stream()
                .map(variantAttributeMapper::maptoDto).toList();
    }

    @Override
    public VariantAttributeDto updateAttribute(Long id, VariantAttributeDto variantAttributeDto) {
        // Tìm đối tượng VariantAttribute hiện tại trong database
        VariantAttribute existingAtt = variantAttributeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variant attribute not found with id: " + id));

        // Cập nhật thông tin của variantAttribute từ variantAttributeDto
        existingAtt.setAttName(variantAttributeDto.getAttName()); // Cập nhật tên thuộc tính từ DTO

        // Lưu lại đối tượng đã cập nhật vào database và trả về DTO tương ứng
        return variantAttributeMapper.maptoDto(variantAttributeRepository.save(existingAtt));
    }

    @Override
    public void deleteAttribute(Long id) {
        if (!variantAttributeRepository.existsById(id)) {
            throw new EntityNotFoundException("Variant attribute not found with id: " + id);
        }
        variantAttributeRepository.deleteById(id);
    }
}
