package com.blog.blogapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blogapp.dao.UserRepo;
import com.blog.blogapp.entity.Users;
import com.blog.blogapp.exception.ResourceNotFoundException;
@Service
public class CustomDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users users = this.userRepo.findByEmail(username).
		orElseThrow(()-> new ResourceNotFoundException("User","email",username));
		

		return users;
	}

}
