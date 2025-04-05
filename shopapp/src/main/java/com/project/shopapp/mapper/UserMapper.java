package com.project.shopapp.mapper;

import com.project.shopapp.dto.UserDto;
import com.project.shopapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto maptoDto(User user);
    User maptoEntity(UserDto dto);
}
