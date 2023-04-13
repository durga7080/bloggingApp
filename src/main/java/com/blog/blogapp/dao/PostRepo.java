package com.blog.blogapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.blogapp.entity.Category;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.Users;

public interface PostRepo extends JpaRepository<Post , Long> {

	List<Post> findByUsers(Users users);
	List<Post> findByCategory(Category category);
	List<Post>  findBytitleContaining(String keyword);
	//@Query("select p from Post where p.title like :key")
	//List<Post>  search(@Param("key") String keyword);
}
