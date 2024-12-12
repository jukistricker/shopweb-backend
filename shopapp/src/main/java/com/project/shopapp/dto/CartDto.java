package com.project.shopapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class CartDto {
    private Long id;
    private UserDto user;
}
