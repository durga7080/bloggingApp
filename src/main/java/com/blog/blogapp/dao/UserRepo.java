package com.blog.blogapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.blogapp.entity.Users;

public interface UserRepo extends JpaRepository<Users, Long>{

	Optional<Users> findByEmail(String email);
	@Query(value = "select name from blog.users where id between :from and :to",nativeQuery = true)
	public List<String> getUserNames(String from,String to);
}
