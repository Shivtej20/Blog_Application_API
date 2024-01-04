package com.Blog_Application.services;

import java.util.List;

import com.Blog_Application.payloads.CategoryDto;
import com.Blog_Application.payloads.PostResponse;

public interface CategoryService {
	
	//Create
	CategoryDto createCategory (CategoryDto categoryDto);
	//Update
	CategoryDto updateCategory (CategoryDto categoryDto, Integer categoryId);
	//Delete
	public void deleteCategory (Integer categoryId);
	//get Single category
	CategoryDto gateCategory (Integer categoryId);
	//get All
	PostResponse getCatagories(Integer pageNumber,Integer pageSizes); 
	

}
