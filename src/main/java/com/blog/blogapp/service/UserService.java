package com.blog.blogapp.service;

import java.util.List;

import com.blog.blogapp.dto.UserDto;

public interface UserService {
	UserDto registerUser(UserDto userDto);
	UserDto createUser(UserDto userDto);
	UserDto updateuser(UserDto userDto,Long userId);
	UserDto getUserById(Long userId);
	List<UserDto> getAllUsers();
	void deleteUser(Long UserId);

}
