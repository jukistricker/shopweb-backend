package com.project.shopapp.dto;



import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {
    private Long id;

    private String cateName;

    private String description;

}
