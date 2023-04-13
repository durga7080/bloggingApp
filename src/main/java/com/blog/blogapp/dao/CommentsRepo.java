package com.blog.blogapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.entity.Comment;

public interface CommentsRepo extends JpaRepository<Comment, Long> {

	
}
