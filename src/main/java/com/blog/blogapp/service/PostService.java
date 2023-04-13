package com.blog.blogapp.service;

import java.util.List;

import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.payload.PostResponce;

public interface PostService {

	//create Post
	PostDto createPost(PostDto postDto,Long userId,Long categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto , Long id);
	//for delete
	void delete(Long id);
	//for get a single user
	PostDto getbypostid(Long id);
	//get all post
	PostResponce getall(Integer pageSIze,Integer pageNumber,String sortBy);
	
	List<PostDto> getPostByCategory(Long catId);
	
	List<PostDto> getPostByUsers(Long userId);
	
	List<PostDto> searchPost(String keyword);
}
