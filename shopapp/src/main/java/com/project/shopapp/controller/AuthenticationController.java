package com.project.shopapp.controller;


import com.nimbusds.jose.JOSEException;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.dto.request.AuthenticationRequest;
import com.project.shopapp.dto.request.IntrospectRequest;
import com.project.shopapp.dto.response.AuthenticationResponse;
import com.project.shopapp.dto.response.IntrospectResponse;
import com.project.shopapp.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {


    private JwtUtils jwtUtils;

    @PostMapping("/token")
    public ResponseEntity<ResponseMessageDto> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse response = jwtUtils.login(authenticationRequest);
            ResponseMessageDto responseMessageDto = new ResponseMessageDto();
            return new ResponseEntity<>(responseMessageDto,HttpStatus.OK);
        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto();
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/introspect")
    public ResponseEntity<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {

            IntrospectResponse response = jwtUtils.introspect(request);
            if (!response.isValid()){
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(response,HttpStatus.OK);


    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        // Token sẽ được gửi dưới dạng Authorization: Bearer <token>
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Cắt bỏ "Bearer " để lấy token thuần túy
        }

        // Gọi service để logout (thêm token vào blacklist)
        jwtUtils.logout(token);

        // Trả về phản hồi khi logout thành công
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }


}
