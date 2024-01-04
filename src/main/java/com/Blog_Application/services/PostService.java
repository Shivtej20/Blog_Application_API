package com.Blog_Application.services;

import java.util.List;
import com.Blog_Application.entity.Post;
import com.Blog_Application.payloads.PostDto;
import com.Blog_Application.payloads.PostResponse;

public interface PostService {
	
	//create Post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	//Update Post
	PostDto updatePost (PostDto postDto,Integer postId);
	//Delete Post
	void deletePost(Integer postId);
	//Get all Post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize , String sortBy, String sortDir);
	//Get Single Post
	PostDto getSingleById (Integer postId);
	//Get all posts by Category
	List<PostDto> getsPostByCategory(Integer categoryId);
	//Get all post by User
	List<PostDto> getsPostByUser(Integer userId);
	//Search post by using keyword
	List<PostDto> searchPosts(String keyword);
}
