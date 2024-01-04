package com.Blog_Application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Blog_Application.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	

}
