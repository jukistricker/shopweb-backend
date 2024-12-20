package com.project.shopapp.dto.response;

import com.project.shopapp.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class IntrospectResponse {
    private boolean valid;
    private String message;
    private Object user;
}
