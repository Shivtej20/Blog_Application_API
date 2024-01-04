package com.Blog_Application.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.internal.bytebuddy.implementation.bytecode.constant.DefaultValue;
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
import com.Blog_Application.payloads.PostResponse;
import com.Blog_Application.payloads.UserDto;
import com.Blog_Application.services.UserService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	// PUT - Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId) {
		UserDto updateUser = this.userService.updateUser(userDto, uId);
		return ResponseEntity.ok(updateUser);
	}
	
	
	// GET - Get Users
	@GetMapping("/allUsers")
	public ResponseEntity<PostResponse> getAllUser(
			@RequestParam (value ="pageNumber",defaultValue="0",required= false)Integer pageNumber,
			@RequestParam (value ="pagesize",defaultValue="2",required= false)Integer pageSize
			) {
		return ResponseEntity.ok(this.userService.getAllUser( pageNumber, pageSize));

	}

	// GET - Get Single User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uId) {
		return ResponseEntity.ok(this.userService.getUserById(uId));

	}

	// DELETE - Delete User
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uId) {
		this.userService.deleteUser(uId);
		 //return new ResponseEntity(Map.of("message","User Deleted Succesfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Delete Succesfully", true), HttpStatus.OK);
	}

}
