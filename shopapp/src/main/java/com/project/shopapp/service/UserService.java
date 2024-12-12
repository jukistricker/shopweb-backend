package com.project.shopapp.service;

import com.project.shopapp.dto.request.AuthenticationRequest;
import com.project.shopapp.dto.UserDto;
import com.project.shopapp.dto.response.AuthenticationResponse;

import java.util.List;


public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id,UserDto userDto);
    void deleteUserById(Long id);

}
