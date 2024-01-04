package com.Blog_Application.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Used for Delete API
public class ApiResponse {
	
	private String message;
	private boolean success;

}
