package com.rahul.actuators.exception.restapi.boot.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rahul.actuators.exception.restapi.boot.dto.UserDto;
import com.rahul.actuators.exception.restapi.boot.entity.User;


@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
