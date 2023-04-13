package com.blog.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.dto.CategoryDto;
import com.blog.blogapp.payload.ApiResponce;
import com.blog.blogapp.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto)
	{
		
		CategoryDto categoryDto2=this.categoryService.cretaeCategory(categoryDto);
		return new  ResponseEntity<>(categoryDto2, HttpStatus.CREATED);		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id)
	{
		CategoryDto categoryDto2  = this.categoryService.updateCategory(categoryDto, id);
		return ResponseEntity.ok(categoryDto2);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getall()
	{
		return ResponseEntity.ok(this.categoryService.findAll());
	
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getuserById(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.categoryService.getCategoryById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletecategorybyId(@PathVariable Long id)
	{
		this.categoryService.delete(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Category Deleted Successfully",true),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteall()
	{
	
		this.categoryService.deleteall();
		return new ResponseEntity<ApiResponce>(new ApiResponce("category deleted",true),HttpStatus.OK);
		
	}
	
}
