package com.project.shopapp.service.impl;

import com.project.shopapp.aspect.Loggable;
import com.project.shopapp.constants.ApiConstants.*;
import com.project.shopapp.dto.CartDto;
import com.project.shopapp.dto.Meta;
import com.project.shopapp.dto.ResponseMessageDto;
import com.project.shopapp.dto.UserDto;
import com.project.shopapp.entity.Cart;
import com.project.shopapp.entity.User;
import com.project.shopapp.mapper.CartMapper;
import com.project.shopapp.mapper.UserMapper;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.CartService;
import com.project.shopapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final CartMapper cartMapper;
  private CartService cartService;

  @Transactional(rollbackFor = Exception.class)
  @Loggable("Đăng ký")
  @Override
  public ResponseMessageDto createUser(UserDto userDto) {
    if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
      userDto.setUsername(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
    }

    if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
      throw new IllegalArgumentException("Email is required");
    }
    if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
      throw new IllegalArgumentException("Password is required");
    }
    if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Email already exists");
    }
    if (userDto.getPassword().length() < 8) {
      throw new IllegalArgumentException("Password must be at least 8 characters");
    }
    if (userDto.getRole() == null) {
      userDto.setRole(User.Role.user);
    }
    User user = userMapper.maptoEntity(userDto);

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    User savedUser = userRepository.save(user);

    Cart newCart = new Cart();
    newCart.setUser(savedUser);

    cartService.createCart(cartMapper.maptoDto(newCart));

    ResponseMessageDto res =
        new ResponseMessageDto(
            new Meta(StatusCode.Success200,
                    "Đăng ký thành công"),
                savedUser);

    return res;
  }

  @Override
  public UserDto updateUser(Long id, UserDto userDto) {
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
      userDto.setUsername(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
    }
    if (userDto.getUsername().length() < 8 || userDto.getUsername().length() > 50) {
      throw new IllegalArgumentException("Username must be between 8-50 characters");
    }

    existingUser.setUsername(userDto.getUsername());

    existingUser.setFullname(userDto.getFullname());
    existingUser.setEmail(userDto.getEmail());
    existingUser.setRole(userDto.getRole());
    return userMapper.maptoDto(userRepository.save(existingUser));
  }

  @Override
  public UserDto getUserById(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return null;
    }
    return userMapper.maptoDto(user);
  }

  @Override
  public UserDto getByEmail(String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null) {
      return null;
    }
    return userMapper.maptoDto(user);
  }

  @Loggable("Lấy danh sách người dùng")
  @Override
  public ResponseMessageDto getAllUsers() {
    List<User> users = userRepository.findAll();
    ResponseMessageDto res = new ResponseMessageDto(
            new Meta(StatusCode.Success200,"Lấy danh sách người dùng thành công"),
            users.stream().map(userMapper::maptoDto).toList()
    );
    return res;
  }

  @Override
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }
}
