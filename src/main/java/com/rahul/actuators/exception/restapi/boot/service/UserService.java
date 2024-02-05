package com.rahul.actuators.exception.restapi.boot.service;

import java.util.List;

import com.rahul.actuators.exception.restapi.boot.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
