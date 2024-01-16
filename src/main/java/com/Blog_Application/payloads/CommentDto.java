package com.Blog_Application.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	

	private int Id;
	private String content;
	
	private UserDto user;

}
