package com.blog.blogapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.dto.UserDto;
import com.blog.blogapp.payload.ApiResponce;
import com.blog.blogapp.service.UserService;
import com.blog.blogapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserServiceImpl impl;
	
	@PostMapping("/add")
	public ResponseEntity<?> createuser(@Valid @RequestBody UserDto userDto)
	{
	UserDto users=this.userService.createUser(userDto);
	return new  ResponseEntity<>(users,HttpStatus.CREATED);
	
	}
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateuser(@Valid @RequestBody UserDto userDto ,@PathVariable Long userId)
	{
		UserDto user=this.userService.updateuser(userDto, userId);
		return ResponseEntity.ok(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteuser(@PathVariable Long userId)
	{
		this.userService.deleteUser(userId);
		//return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponce>(new ApiResponce("user deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getall()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getuserbyid(@PathVariable Long userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	@GetMapping("/get/{from}/{to}")
	public ResponseEntity<?> getusername(@PathVariable String from,@PathVariable String to)
	{
		return ResponseEntity.ok(this.impl.getuserr(from, to));
	}
	
}
