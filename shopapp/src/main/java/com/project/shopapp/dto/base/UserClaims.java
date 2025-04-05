package com.project.shopapp.dto.base;

import com.project.shopapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserClaims {
    private long id;
    private String role;
    private String fullname;
    private String email;
}
