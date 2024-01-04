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
import com.Blog_Application.payloads.ApiResponse;
import com.Blog_Application.payloads.CategoryDto;
import com.Blog_Application.payloads.PostResponse;
import com.Blog_Application.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
		//POST - create category
		@PostMapping("/")
		public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
			CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
			return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
		}
	
		// PUT - Update category
		@PutMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable("categoryId") Integer catId) {
			CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
			return ResponseEntity.ok(updateCategory);
		}
		
		// GET - Get categories
		@GetMapping("/getAllCategory")
		public ResponseEntity<PostResponse> getAllCategory(
				@RequestParam(value= "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
				@RequestParam(value = "pageSize",defaultValue = "2",required = false)Integer pageSize
				) {
			return ResponseEntity.ok(this.categoryService.getCatagories(pageNumber, pageSize));

		}

		// GET - Get Single Category
		@GetMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryId") Integer catId) {
			return ResponseEntity.ok(this.categoryService.gateCategory(catId));

		}
		
		// DELETE - Delete Category
		@DeleteMapping("/{categoryId}")
		public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer catId) {
			this.categoryService.deleteCategory(catId);
			 //return new ResponseEntity(Map.of("message","Category Deleted Succesfully"),HttpStatus.OK);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Succesfully", true), HttpStatus.OK);
		}

}
