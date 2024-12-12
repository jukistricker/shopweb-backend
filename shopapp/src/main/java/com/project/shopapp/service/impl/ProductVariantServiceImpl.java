package com.project.shopapp.service.impl;

import com.project.shopapp.dto.ProductVariantDto;
import com.project.shopapp.dto.VariantAttributeDto;
import com.project.shopapp.entity.ProductVariant;
import com.project.shopapp.entity.VariantAttribute;
import com.project.shopapp.mapper.ProductVariantMapper;
import com.project.shopapp.mapper.VariantAttributeMapper;
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

    @Override
    public ProductVariantDto createVariant(ProductVariantDto productVariantDto) {
        ProductVariant newProductVariant = ProductVariantMapper.maptoEntity(productVariantDto);
        return ProductVariantMapper.maptoDto(productVariantRepository.save(newProductVariant));
    }

    @Override
    public ProductVariantDto getVariant(Long id) {
        ProductVariant productVariant = productVariantRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product variant not found with id: " + id));
        return ProductVariantMapper.maptoDto(productVariant);
    }

    @Override
    public List<ProductVariantDto> getAllVariants() {
        List<ProductVariant> productVariants = productVariantRepository.findAll();
        return productVariants.stream()
                .map(ProductVariantMapper::maptoDto).toList();
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
        return VariantAttributeMapper.maptoDto(variantAttribute);

    }

    @Override
    public VariantAttributeDto createAttribute(VariantAttributeDto variantAttributeDto) {
        VariantAttribute newVariantAttribute = VariantAttributeMapper.maptoEntity(variantAttributeDto);
        return VariantAttributeMapper.maptoDto(variantAttributeRepository.save(newVariantAttribute));
    }

    @Override
    public List<VariantAttributeDto> getAllAttributes() {

        List<VariantAttribute> variantAttributes = variantAttributeRepository.findAll();
        System.out.println(variantAttributes);
        return variantAttributes.stream()
                .map(VariantAttributeMapper::maptoDto).toList();
    }

    @Override
    public VariantAttributeDto updateAttribute(Long id, VariantAttributeDto variantAttributeDto) {
        VariantAttribute existingAtt = variantAttributeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variant attribute not found with id: " + id));
        existingAtt.setAttName(existingAtt.getAttName());
        return VariantAttributeMapper.maptoDto(variantAttributeRepository.save(existingAtt));
    }

    @Override
    public void deleteAttribute(Long id) {
        if (!variantAttributeRepository.existsById(id)) {
            throw new EntityNotFoundException("Variant attribute not found with id: " + id);
        }
        variantAttributeRepository.deleteById(id);
    }
}
