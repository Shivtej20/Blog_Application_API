package com.Blog_Application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.entity.Category;
import com.Blog_Application.entity.Post;
import com.Blog_Application.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	//Creating Custom finder methods using JPA in repository
	List<Post> findByUser(User user); 
	List<Post> findByCategory(Category category); 
}
