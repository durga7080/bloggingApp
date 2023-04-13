package com.blog.blogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapp.dao.CategoryRepo;
import com.blog.blogapp.dto.CategoryDto;
import com.blog.blogapp.entity.Category;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	

	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CategoryDto cretaeCategory(CategoryDto categoryDto) {
		Category category = this.dtotocategory(categoryDto);
		 Category saveCategory=this.categoryRepo.save(category);
		
		return this.categoryToDto(saveCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		category.setCategoryDiscription(categoryDto.getCategoryDiscription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		Category category2=this.categoryRepo.save(category);
		CategoryDto categoryDto2=this.categoryToDto(category2);
		return categoryDto2;
	}

	@Override
	public List<CategoryDto> findAll() {

		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos=categories.stream().map(category->this.categoryToDto(category)).collect(Collectors.toList());
		
		return categoryDtos;
	}

	@Override
	public void delete(Long id) {

		Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		this.categoryRepo.delete(category);
	}

	@Override
	public void deleteall(){
		// TODO Auto-generated method stub
		this.categoryRepo.deleteAll();
		
	}
	
	@Override
	public CategoryDto getCategoryById(Long id) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		
		return this.categoryToDto(category);
	}
	
	public Category dtotocategory(CategoryDto categoryDto)
	{
		Category category = this.modelMapper.map(categoryDto,Category.class);
		return category;
	}

	public CategoryDto categoryToDto(Category category)
	{
		CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
		return categoryDto;
	}
}
