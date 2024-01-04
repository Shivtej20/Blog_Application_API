package com.Blog_Application.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter

//User For Pagination response
public class PostResponse {
	
	private List<PostDto> PostContent;
	private List<CategoryDto> CategoryContent;
	private List<UserDto> UserContent;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;

}
