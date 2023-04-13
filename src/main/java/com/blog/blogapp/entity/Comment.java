package com.blog.blogapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentId;
	private String comments_post;
	
	@ManyToOne 
	@JoinColumn(name = "post_id")
	private Post post;

	public Comment(Long commentId, String comments_post, Post post) {
		super();
		this.commentId = commentId;
		this.comments_post = comments_post;
		this.post = post;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getComments_post() {
		return comments_post;
	}

	public void setComments_post(String comments_post) {
		this.comments_post = comments_post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	
}
