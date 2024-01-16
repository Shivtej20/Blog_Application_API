package com.Blog_Application.services.Imple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.Blog_Application.entity.User;
import com.Blog_Application.exceptions.ResourceNotFoundException;
import com.Blog_Application.payloads.PostResponse;
import com.Blog_Application.payloads.UserDto;
import com.Blog_Application.repositories.UserRepo;
import com.Blog_Application.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
	
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 =this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

		return this.userToDto(user);
	}

	@Override
	public PostResponse getAllUser(Integer pageNumber,Integer pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
		Page<User> page = this.userRepo.findAll(pageRequest);
		List<User> content = page.getContent();
		List<UserDto> userDtos = content.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		//List<User> users =this.userRepo.findAll();
		PostResponse postResponse =new PostResponse();
		
		postResponse.setUserContent(userDtos);
		postResponse.setPageNumber(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElements(page.getTotalElements());
		postResponse.setTotalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());
		
		
		
		return postResponse;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
		
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		Hard Coded Conversation of UserDto to User
//		User user =new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto =this.modelMapper.map(user, UserDto.class);
		
//		Hard Coded Conversation of User to UserDto
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());;
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
