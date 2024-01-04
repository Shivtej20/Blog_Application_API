package com.Blog_Application.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post Table")
@NoArgsConstructor
@Getter
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	@Column(name = "Title" ,nullable = false)
	private String title;
	@Column(name = "content" ,nullable = false, length = 10000)
	private String content;
	private String imageName;
	private Date addedDate;

	//Makiing connections
	@ManyToOne
	//@JoinColumn(name= "Category ID") this is used to Rename the table
	private Category category;
	@ManyToOne
	private User user;

}
