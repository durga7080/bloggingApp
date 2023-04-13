package com.blog.blogapp.service;

import java.util.List;

import com.blog.blogapp.dto.CategoryDto;

public interface CategoryService {

	CategoryDto cretaeCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto ,Long id);
	List<CategoryDto> findAll();
	void delete(Long id);
	CategoryDto getCategoryById(Long id);
	void deleteall();
}
