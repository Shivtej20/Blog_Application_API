package com.Blog_Application.services.Imple;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.Blog_Application.entity.Category;
import com.Blog_Application.entity.Post;
import com.Blog_Application.entity.User;
import com.Blog_Application.exceptions.ResourceNotFoundException;
import com.Blog_Application.payloads.PostDto;
import com.Blog_Application.payloads.PostResponse;
import com.Blog_Application.repositories.CategoryRepo;
import com.Blog_Application.repositories.PostRepo;
import com.Blog_Application.repositories.UserRepo;
import com.Blog_Application.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	//createPost
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User ID ", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category ID ", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post post2 = this.postRepo.save(post);
		return this.modelMapper.map(post2, PostDto.class);
	}
	
	//updatePost
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post1 = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id ", postId));
		post1.setTitle(postDto.getTitle());
		post1.setContent(postDto.getContent());
		post1.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post1);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	//deletePost
	@Override
	public void deletePost(Integer postId) {
		//this.postRepo.deleteById(postId);
		Post post1 = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id ", postId));
		this.postRepo.delete(post1);

	}
	
	//getAllPost
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy ,String sortDir) {
		//Sorting 1 st way
		Sort sort= (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		//Sorting 2 nd way
//		Sort sort =null;
//		if(sortDir.equalsIgnoreCase("asc"))
//		{
//			sort = Sort.by(sortBy).ascending();
//		}
//		else {
//			sort =Sort.by(sortBy).descending();
//		}
		
		//Pagination
		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		//List<Post> findAllPosts = this.postRepo.findAll();
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		//To return postResponse object 
		PostResponse postResponse=new PostResponse();
		
		postResponse.setPostContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}
	
	//get Single post ById
	@Override
	public PostDto getSingleById(Integer postId) {
		Post post1 = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID ", postId));
		PostDto postDto1 = this.modelMapper.map(post1, PostDto.class);
		return  postDto1;
	}

	//get Post By Category
	@Override
	public List<PostDto> getsPostByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", " Category ID ", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> PostsDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return PostsDtos;
	}

	//get Post By User
	@Override
	public List<PostDto> getsPostByUser(Integer userId) {
		
		User user1 = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "user ID ", userId));
		List<Post> userPosts = this.postRepo.findByUser(user1);
		List<PostDto> PostsDtos = userPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return PostsDtos;
	}

	//searchPosts
	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		//this.postRepo.findBy(null, null)
		return null;
	}

}
