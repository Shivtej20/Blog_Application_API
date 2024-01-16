package com.Blog_Application.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Blog_Application.entity.Category;
import com.Blog_Application.entity.Comment;
import com.Blog_Application.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private int postId;
	
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	private UserDto user;
	
	private List<CommentDto> comments = new ArrayList<>();
}
