package com.Blog_Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Blog_Application.config.AppConstant;
import com.Blog_Application.entity.Post;
import com.Blog_Application.payloads.ApiResponse;
import com.Blog_Application.payloads.PostDto;
import com.Blog_Application.payloads.PostResponse;
import com.Blog_Application.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postService;
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//update Post By Id
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId,@RequestBody PostDto postDto){
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto> (updatePost, HttpStatus.OK);
	}
	
	//Get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity< List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> postByUser = this.postService.getsPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser , HttpStatus.OK);
		
	}
	
	//Get By Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> getsPostByCategory = this.postService.getsPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>> (getsPostByCategory, HttpStatus.OK);
	}
	
	//get by post id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto singlePostById = this.postService.getSingleById(postId);
		return new ResponseEntity<PostDto> (singlePostById, HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/allPosts")
	public ResponseEntity <PostResponse> getAllPost(
			@RequestParam(value ="pageNumber",defaultValue =AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value ="pageSize",defaultValue =AppConstant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value= "sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
			@RequestParam(value= "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir
			
			){
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse> (postResponse , HttpStatus.OK);
	}
	
	//Delete post
	@DeleteMapping("/{postId}")
	/*public void deletepost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
	}*/
	public ResponseEntity<ApiResponse> deletepost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true),HttpStatus.OK);
	}
	
	//Searching
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByTitle (
			@PathVariable("keyword") String keyword){
		
		List<PostDto> searchPosts = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(searchPosts ,HttpStatus.OK);
		
	}
	
}
