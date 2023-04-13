package com.blog.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.dto.UserDto;
import com.blog.blogapp.exception.BadCredintial;
import com.blog.blogapp.payload.JwtAuthRequest;
import com.blog.blogapp.payload.JwtAuthResponse;
import com.blog.blogapp.security.JwtHealper;
import com.blog.blogapp.service.UserService;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
	@Autowired
	private JwtHealper jwtHealper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtHealper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		System.out.println("jwt healper :"+this.jwtHealper.getUsernameFromToken(token));
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}

	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}
		catch (BadCredentialsException e) {
			System.out.println("username and password is invalid");
			throw new BadCredintial("invalid Username and password!!");
			
		}
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto)
	{
		UserDto registerUser = this.userService.registerUser(userDto);
		return new  ResponseEntity<>(registerUser,HttpStatus.CREATED);
	}
}
