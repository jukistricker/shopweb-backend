package com.project.shopapp.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, length = 12,nullable = true)
    private String username = UUID.randomUUID().toString();

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Password length is at least 8 characters")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role = Role.user;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "active",nullable = false, columnDefinition = "BIT(1) DEFAULT b'1'")
    private boolean active = true;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    public enum Role {
        admin, user, seller
    }

    public User(Long id,String username, String email, String password, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}










