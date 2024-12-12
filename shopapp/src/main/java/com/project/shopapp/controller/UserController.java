package com.project.shopapp.controller;


import com.project.shopapp.dto.request.AuthenticationRequest;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.dto.UserDto;
import com.project.shopapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;


@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessageDto> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto savedUser = userService.createUser(userDto);
            ResponseMessageDto response = new ResponseMessageDto("Create user success",savedUser,true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto("Create user failed: ",e.getMessage(),false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> allUsers = userService.getAllUsers();
            ResponseMessageDto response = new ResponseMessageDto("get all users success",allUsers,true);

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
            ResponseMessageDto response = new ResponseMessageDto("Update user success",updatedUser,true);
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
            ResponseMessageDto response = new ResponseMessageDto("Delete user success",user_id,true);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch (Exception e) {
            ResponseMessageDto response = new ResponseMessageDto("Delete user failed: ",e.getMessage(),false);
            return new ResponseEntity<>( response,HttpStatus.BAD_REQUEST);
        }
     }


}
