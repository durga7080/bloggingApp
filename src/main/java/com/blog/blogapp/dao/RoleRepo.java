package com.blog.blogapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
