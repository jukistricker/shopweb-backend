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

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private boolean active;

    private String phone_number;


}
