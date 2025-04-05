package com.project.shopapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cate_name",unique = true, nullable = false, length = 100)
    private String cateName;

    @Column(name = "description")
    private String description;


}
