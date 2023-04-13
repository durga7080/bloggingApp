package com.blog.blogapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Long> {

}
