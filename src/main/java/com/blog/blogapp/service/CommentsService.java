package com.blog.blogapp.service;

import com.blog.blogapp.dto.CommentsDto;

public interface CommentsService {

	CommentsDto create(CommentsDto commentsDto,Long postId);
	void delete(Long id);
}
