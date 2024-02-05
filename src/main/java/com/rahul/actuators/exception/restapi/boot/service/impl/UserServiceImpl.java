package com.rahul.actuators.exception.restapi.boot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.actuators.exception.restapi.boot.dto.UserDto;
import com.rahul.actuators.exception.restapi.boot.entity.User;
import com.rahul.actuators.exception.restapi.boot.exception.EmailAlreadyExistsException;
import com.rahul.actuators.exception.restapi.boot.exception.ResourceNotFoundException;
import com.rahul.actuators.exception.restapi.boot.mapper.AutoUserMapper;
import com.rahul.actuators.exception.restapi.boot.repository.UserRepository;
import com.rahul.actuators.exception.restapi.boot.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

	 private UserRepository userRepository;

	    private ModelMapper modelMapper;

	    @Override
	    public UserDto createUser(UserDto userDto) {

	        // Convert UserDto into User JPA Entity
	       // User user = UserMapper.mapToUser(userDto);

	        //User user = modelMapper.map(userDto, User.class);

	        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

	        if(optionalUser.isPresent()){
	            throw new EmailAlreadyExistsException("Email Already Exists for User");
	        }

	        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

	        User savedUser = userRepository.save(user);

	        // Convert User JPA entity to UserDto
	        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

	        //UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

	        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

	        return savedUserDto;
	    }

	    @Override
	    public UserDto getUserById(Long userId) {
	        User user = userRepository.findById(userId).orElseThrow(
	                () -> new ResourceNotFoundException("User", "id", userId)
	        );
	        //return UserMapper.mapToUserDto(user);
	        //return modelMapper.map(user, UserDto.class);
	        return AutoUserMapper.MAPPER.mapToUserDto(user);
	    }

	    @Override
	    public List<UserDto> getAllUsers() {
	        List<User> users = userRepository.findAll();
//	        return users.stream().map(UserMapper::mapToUserDto)
//	                .collect(Collectors.toList());

//	        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
//	                .collect(Collectors.toList());

	        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public UserDto updateUser(UserDto user) {

	        User existingUser = userRepository.findById(user.getId()).orElseThrow(
	                () -> new ResourceNotFoundException("User", "id", user.getId())
	        );

	        existingUser.setFirstName(user.getFirstName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setEmail(user.getEmail());
	        User updatedUser = userRepository.save(existingUser);
	        //return UserMapper.mapToUserDto(updatedUser);
	        //return modelMapper.map(updatedUser, UserDto.class);
	        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
	    }

	    @Override
	    public void deleteUser(Long userId) {

	        User existingUser = userRepository.findById(userId).orElseThrow(
	                () -> new ResourceNotFoundException("User", "id", userId)
	        );

	        userRepository.deleteById(userId);
	    }
	}
