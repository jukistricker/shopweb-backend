package com.project.shopapp.dto;


import com.project.shopapp.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
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

    @Size(max = 50, message = "Username must be at most 50 characters")
    private String username;

    @NotBlank(message = "Fullname is required")
    @Size(max = 100, message = "Fullname must be at most 100 characters")
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @NotNull(message = "Role is required")
    private User.Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean active;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
    private String phoneNumber;


}
