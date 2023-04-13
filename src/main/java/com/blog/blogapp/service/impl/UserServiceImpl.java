package com.blog.blogapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.blogapp.config.AppConstants;
import com.blog.blogapp.dao.RoleRepo;
import com.blog.blogapp.dao.UserRepo;
import com.blog.blogapp.dto.UserDto;
import com.blog.blogapp.entity.Role;
import com.blog.blogapp.entity.Users;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper  modelMapper;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		Users user = this.dtoToUser(userDto);
		Users saveUser = this.userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateuser(UserDto userDto, Long userId) {
		Users user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId)); 
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		Users user1 =this.userRepo.save(user);
		UserDto userDto2=this.userToDto(user1);
		return userDto2;
	}

	@Override
	public UserDto getUserById(Long userId) {
		Users user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<Users> users = this.userRepo.findAll();
		// users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList()));
		List<UserDto> userDtos =   users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Long UserId) {
		Users user= this.userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User","id", UserId));
		this.userRepo.delete(user);
		

	}
	
	private Users dtoToUser(UserDto user)
	{
		Users users = this.modelMapper.map(user, Users.class);
		/*
		 * users.setId(user.getId()); users.setName(user.getName());
		 * users.setEmail(user.getEmail()); users.setPassword(user.getPassword());
		 * users.setAbout(user.getAbout());
		 */
		return users;
		
	}
	private UserDto userToDto(Users users)
	{
		UserDto userDto = this.modelMapper.map(users, UserDto.class);
		/*
		 * userDto.setId(users.getId()); userDto.setName(users.getName());
		 * userDto.setPassword(users.getPassword()); userDto.setEmail(users.getEmail());
		 * userDto.setAbout(users.getAbout());
		 */
		return userDto; 
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
		// TODO Auto-generated method stub\
		Users users = this.modelMapper.map(userDto, Users.class);
		users.setPassword(this.passwordEncoder.encode(users.getPassword()));
		 Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
		 users.getRoles().add(role);
		 Users save = this.userRepo.save(users);
		 
		
		return this.modelMapper.map(save, UserDto.class);
	}
	
	public Map<String, Object> getuserr(String from,String to)
	{
		Map<String , Object> gretall=new HashMap<>();
		List<String> userNames = userRepo.getUserNames(from, to);
		gretall.put("names", userNames);	
		return gretall;
	}

}
