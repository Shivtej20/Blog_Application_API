package com.Blog_Application.services;

import java.util.List;

import com.Blog_Application.payloads.PostResponse;
import com.Blog_Application.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	PostResponse getAllUser(Integer pageNumber,Integer pageSize);
	void deleteUser (Integer userId);

}
