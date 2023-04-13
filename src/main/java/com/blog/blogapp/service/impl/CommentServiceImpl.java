package com.blog.blogapp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapp.dao.CommentsRepo;
import com.blog.blogapp.dao.PostRepo;
import com.blog.blogapp.dto.CommentsDto;
import com.blog.blogapp.entity.Comment;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.service.CommentsService;
@Service
public class CommentServiceImpl implements CommentsService {
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CommentsRepo commentsRepo;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public CommentsDto create(CommentsDto commentsDto,Long postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));
		Comment comment = this.mapper.map(commentsDto, Comment.class);
		comment.setPost(post);
		//comment.setComments_post("this is a page static");
		Comment save1 = this.commentsRepo.save(comment);
		CommentsDto commentsDto2 = this.mapper.map(save1,CommentsDto.class);
		
		return commentsDto2;
	}

	@Override
	public void delete(Long id) {

		Comment comment = this.commentsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment", "id", id));
		this.commentsRepo.delete(comment);
	}

}
