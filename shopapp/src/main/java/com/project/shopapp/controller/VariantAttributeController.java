package com.project.shopapp.controller;

import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.dto.VariantAttributeDto;
import com.project.shopapp.service.ProductVariantSerivce;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated @AllArgsConstructor
@RequestMapping("api/v1/attribute")
public class VariantAttributeController {
    private ProductVariantSerivce productVariantSerivce;

    @PostMapping("/create")
    public ResponseEntity<VariantAttributeDto> createAttribute(@Validated @RequestBody VariantAttributeDto dto) {
        try {
            System.out.println(dto);
            VariantAttributeDto createdAttribute = productVariantSerivce.createAttribute(dto);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Create attribute successfully",createdAttribute,true);
            return new ResponseEntity<>(createdAttribute, HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Create attribute failed",e.getMessage(),false);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<VariantAttributeDto> updateAttribute(@Validated @PathVariable("id") Long id,@RequestBody VariantAttributeDto variantAttributeDto) {
        try {
            VariantAttributeDto updatedAttribute = productVariantSerivce.updateAttribute(id, variantAttributeDto);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Update attribute successfully",updatedAttribute,true);
            return new ResponseEntity<>(updatedAttribute, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Update attribute failed",e.getMessage(),false);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<VariantAttributeDto> getAttributeById(@PathVariable("id") Long id) {
        try {
            VariantAttributeDto variantAttributeDto = productVariantSerivce.getAttribute(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Get attribute successfully",variantAttributeDto,true);
            return new ResponseEntity<>(variantAttributeDto, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Get attribute failed",e.getMessage(),false);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<VariantAttributeDto>> getAllAttributes(@PathVariable Long id) {
        try {
            List<VariantAttributeDto> allAttributes = productVariantSerivce.getAllAttributes(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Get all attributes successfully",allAttributes,true);
            return new ResponseEntity<>(allAttributes, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Get all attributes failed",e.getMessage(),false);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseMessageDto> deleteAttribute(@PathVariable("id") Long id) {
        try {
            productVariantSerivce.deleteAttribute(id);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Delete attribute successfully",id,true);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto responseMessageDto = new ResponseMessageDto("Delete attribute failed",e.getMessage(),false);
            return new ResponseEntity<>(responseMessageDto, HttpStatus.BAD_REQUEST);
        }
    }

}
