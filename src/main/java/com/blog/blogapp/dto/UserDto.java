package com.blog.blogapp.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blog.blogapp.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserDto {
	
	private Long id;
	@NotEmpty( message = "This field is required")
	private String name;
	@Email
	private String email;
	@NotEmpty(message = "this field is required")
	@Size(min = 3,max = 10,message = "password should be between 3-10")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotEmpty(message = "this field is required")
	private String about;
	
	private Set<RoleDto>roles = new HashSet<>();
	
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	

	
}
