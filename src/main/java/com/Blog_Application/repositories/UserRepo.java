package com.Blog_Application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Blog_Application.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
