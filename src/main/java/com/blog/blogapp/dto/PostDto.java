package com.blog.blogapp.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostDto {

	private Long postId;
	private String title;    
	private String content;
	private String imageName;
	private Date date;
	private Set<CommentsDto> comments=new HashSet<>();
	public PostDto(Long postId, String title, String content, String imageName, Date date, Set<CommentsDto> comments) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.date = date;
		this.comments = comments;
	}
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Set<CommentsDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentsDto> comments) {
		this.comments = comments;
	}
	
}
