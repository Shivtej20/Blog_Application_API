package com.Blog_Application.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer Id;
	@NotBlank( message = "Minimum 4 characters required")
	@Size(min =4)
	private String categoryTitle;
	@NotBlank
	@Size(min =10, message = "Minimum 10 characters required")
	private String categoryDescription;

	
}
