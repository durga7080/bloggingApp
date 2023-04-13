package com.blog.blogapp.dto;

public class CommentsDto {

	private Long commentId;
	private String comments_post;
	public CommentsDto(Long commentId, String comments_post) {
		super();
		this.commentId = commentId;
		this.comments_post = comments_post;
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
	public CommentsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
