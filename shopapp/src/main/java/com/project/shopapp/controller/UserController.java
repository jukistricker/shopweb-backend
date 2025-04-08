package com.project.shopapp.controller;


import com.project.shopapp.dto.CartDto;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.dto.UserDto;
import com.project.shopapp.service.CartService;
import com.project.shopapp.service.UserService;
import com.project.shopapp.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private CartService cartService;
    private JwtUtils jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessageDto> createUser(@RequestBody UserDto userDto) {
        try {
            ResponseMessageDto res = userService.createUser(userDto);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> allUsers = userService.getAllUsers();
            ResponseMessageDto response = new ResponseMessageDto();

            var authentication = SecurityContextHolder.getContext().getAuthentication();

            System.out.println(authentication.getName());
            authentication.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));

            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
           return new ResponseEntity<>( null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{user_id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long user_id) {
        try {
            System.out.println(user_id);
            UserDto user = userService.getUserById(user_id);
            if (user != null) {
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
            else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateById/{user_id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("user_id") Long user_id, @RequestBody UserDto userDto) {
        try {

            UserDto updatedUser = userService.updateUser(user_id, userDto);
            ResponseMessageDto response = new ResponseMessageDto();
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>( null,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{user_id}")
    public ResponseEntity<ResponseMessageDto> deleteUserById(@PathVariable("user_id") Long user_id) {
        try {
            userService.deleteUserById(user_id);
            ResponseMessageDto response = new ResponseMessageDto();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto();
            return new ResponseEntity<>( response,HttpStatus.BAD_REQUEST);
        }
     }


}
