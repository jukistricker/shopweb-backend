package com.project.shopapp.dto;


import com.project.shopapp.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username ;

    private String fullname;

    private String email;

    private String password;

    private User.Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean active;

    private String phoneNumber;


}
