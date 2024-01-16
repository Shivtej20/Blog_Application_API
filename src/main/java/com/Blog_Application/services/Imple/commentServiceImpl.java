package com.Blog_Application.services.Imple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog_Application.entity.Comment;
import com.Blog_Application.entity.Post;
import com.Blog_Application.entity.User;
import com.Blog_Application.exceptions.ResourceNotFoundException;
import com.Blog_Application.payloads.CommentDto;
import com.Blog_Application.repositories.CommentRepo;
import com.Blog_Application.repositories.PostRepo;
import com.Blog_Application.repositories.UserRepo;
import com.Blog_Application.services.CommentService;
@Service
public class commentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User ID ", userId));
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setUser(user);
		comment.setPost(post);
		
		Comment saveComment = this.commentRepo.save(comment);
		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepo.delete(comment);
		
	}

}
