package com.Blog_Application.services.Imple;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.Blog_Application.entity.Category;
import com.Blog_Application.exceptions.ResourceNotFoundException;
import com.Blog_Application.payloads.CategoryDto;
import com.Blog_Application.payloads.PostResponse;
import com.Blog_Application.repositories.CategoryRepo;
import com.Blog_Application.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	public CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	//Create
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}
	
	//update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category ", "Category ID ", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updateCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}
	
	//delete
	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category ID ", categoryId));
		this.categoryRepo.delete(cat);
	}
	
	//get single
	@Override
	public CategoryDto gateCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category ID ", categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}
	
	//get All
	@Override
	public PostResponse getCatagories(Integer pageNumber,Integer pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
		Page<Category> findAll = this.categoryRepo.findAll(pageRequest);
		List<Category> list = findAll.getContent();
		//List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = list.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setCategoryContent(catDtos);
		postResponse.setPageNumber(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());
		
		
		
		return postResponse;
	}

}
