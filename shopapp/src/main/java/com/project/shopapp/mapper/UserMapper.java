package com.project.shopapp.mapper;

import com.project.shopapp.dto.UserDto;
import com.project.shopapp.entity.User;

public class UserMapper {
    public static UserDto maptoDto(User user) {

        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getCreated_at(),
                user.getUpdated_at(),
                user.isActive(),
                user.getPhone_number()
        );
    }

    public static User maptoEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole()

        );
    }
}
