package com.blog.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.dto.CommentsDto;
import com.blog.blogapp.payload.ApiResponce;
import com.blog.blogapp.service.CommentsService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentsService commentsService;
	
	@PostMapping("/post/{postId}/add")
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentsDto,@PathVariable Long postId)
	{
		System.out.println(commentsDto.getComments_post());
		CommentsDto create = this.commentsService.create(commentsDto, postId);
		
		return new ResponseEntity<CommentsDto>(create,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id)
	{
		this.commentsService.delete(id);
		return new ResponseEntity<ApiResponce>( new ApiResponce("user Deleted Sucessfully",true),HttpStatus.OK);
	}
}
