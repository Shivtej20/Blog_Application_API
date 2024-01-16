package com.Blog_Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Blog_Application.payloads.ApiResponse;
import com.Blog_Application.payloads.CommentDto;
import com.Blog_Application.services.CommentService;
@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<CommentDto> createComment (@RequestBody CommentDto commentDto,@PathVariable Integer postId,@PathVariable Integer userId){
		CommentDto createComment = this.commentService.createComment(commentDto, postId,userId);
		return new ResponseEntity<CommentDto> (createComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment (@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Delete Succesfully", true), HttpStatus.OK);
	}

}
