package com.blog.blogapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogapp.config.AppConstants;
import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.payload.ApiResponce;
import com.blog.blogapp.payload.PostResponce;
import com.blog.blogapp.service.FileService;
import com.blog.blogapp.service.PostService;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	@Autowired
	FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{UserId}/category/{catId}/add")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Long UserId,@PathVariable Long catId)
	{
		PostDto create = this.postService.createPost(postDto, UserId, catId);
		return new ResponseEntity<PostDto>(create,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{id}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long id)
	{
		
		List<PostDto> dtos =this.postService.getPostByUsers(id);
		System.out.println(dtos.get(1).getTitle());
		System.out.println(dtos.size());
		return new ResponseEntity<List<PostDto>>(dtos,HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("/category/{catid}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long catid)
	{
		List<PostDto> dtoss=this.postService.getPostByCategory(catid);
		return new ResponseEntity<List<PostDto>>(dtoss,HttpStatus.OK);
				
	}
	
	//for get all post
	@GetMapping("/getall")
	public ResponseEntity<PostResponce> findall(@RequestParam(value ="pageSize",defaultValue = AppConstants.PAZE_SIZE,required = false) Integer pageSize,
			@RequestParam( value ="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy)
	{
		PostResponce postResponce = this.postService.getall(pageSize, pageNumber,sortBy);
		//List<PostDto> dtos=this.postService.getall();
		return new ResponseEntity<PostResponce>(postResponce,HttpStatus.OK);
	}
	
	
	@GetMapping("/post/{id}")
	public ResponseEntity<?> findbyid(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.postService.getbypostid(id));
	}
	
	//for delte by id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletebyid(@PathVariable Long id)
	{
		this.postService.delete(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Post Deleted Succesfully",true),HttpStatus.OK);
	}
	//for update
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePost(@RequestBody PostDto postDto,@PathVariable Long id)
	{
	 PostDto postDto2=this.postService.updatePost(postDto, id);
	 return  ResponseEntity.ok(postDto2);
	}
	
	//Search
	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> search(@PathVariable String keyword)
	{
		//List<PostDto> searchPost = this.postService.searchPost("%"+keyword+"%");
		List<PostDto> searchPost = this.postService.searchPost(keyword);
		return ResponseEntity.ok(searchPost);
	}
	//post image upload
	@PostMapping("image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Long postId) throws IOException
	{
		PostDto postDto = this.postService.getbypostid(postId);
		String filename = this.fileService.uploadImage(path, image);
		
		postDto.setImageName(filename);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping("/image/{imageName}")
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException
	{
		InputStream resourse = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourse, response.getOutputStream());
	
	}
}
