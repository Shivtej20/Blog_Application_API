package com.Blog_Application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
