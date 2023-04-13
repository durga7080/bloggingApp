package com.blog.blogapp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blogapp.dao.CategoryRepo;
import com.blog.blogapp.dao.PostRepo;
import com.blog.blogapp.dao.UserRepo;
import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.entity.Category;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.Users;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.payload.PostResponce;
import com.blog.blogapp.service.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	UserRepo userRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto,Long userId,Long categoryId) {

		Users users = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
		
		
		Post post=this.mapper.map(postDto, Post.class);
		post.setImagename("default.png");
		post.setAddDate(new Date());
		post.setUsers(users);
		post.setCategory(category);
		
		Post post2=this.postRepo.save(post);
		
		return this.mapper.map(post2, PostDto.class);
	}


	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImagename(postDto.getImageName());
		Post post2 = this.postRepo.save(post);
		return this.mapper.map(post2, PostDto.class);
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		this.postRepo.delete(post);
	}


	@Override
	public PostDto getbypostid(Long id) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		PostDto postDto = this.mapper.map(post,PostDto.class);
		return postDto;
	}


	@Override
	public PostResponce getall(Integer pageSIze,Integer pageNumber,String sortBy) {
		// TODO Auto-generated method stub
		Pageable p = PageRequest.of(pageSIze, pageNumber, Sort.by(sortBy).ascending());
		
		Page<Post> findall=this.postRepo.findAll(p);
		List<Post> posts=findall.getContent();
		
		List<PostDto> dtos = posts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postResponce= new PostResponce();
		postResponce.setContent(dtos);
		postResponce.setPageNumber(findall.getNumber());
		postResponce.setPageSize(findall.getSize());
		postResponce.setTotalElements(findall.getTotalElements());
		postResponce.setTotalPages(findall.getTotalPages());
		postResponce.setLastPage(findall.isLast());
		return postResponce;
	}


	@Override
	public List<PostDto> getPostByCategory(Long catId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("category","id", catId));
		List<Post>posts=this.postRepo.findByCategory(category);
		List<PostDto> postdtoo=posts.stream().map(post->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtoo;
	}


	@Override
	public List<PostDto> getPostByUsers(Long userId) {
		// TODO Auto-generated method stub
		Users users = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		System.out.println("hhhhhhhh"+users.getPosts().get(0).getTitle());
		List<Post> posts= this.postRepo.findByUsers(users);

		List<PostDto> postDtos = posts.stream().map(post-> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}


	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> containing = this.postRepo.findBytitleContaining(keyword);
		List<PostDto> collect = containing.stream().map(post -> (this.mapper.map(post,PostDto.class))).collect(Collectors.toList());
		
		return collect;
	}
	

	
}
