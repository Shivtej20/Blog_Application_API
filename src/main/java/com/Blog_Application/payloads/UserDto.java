package com.Blog_Application.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4, message = "User name must be greater than 4 Characters...")
	private String Name;
	@Email(message = "Must be a valid E-mail")
	private String Email;
	//Don't want to show user password 
	@NotEmpty
	@Size(min = 3, max= 10 ,message = "Password must be min 3 and Max10 characters")
	private String Password;
	@NotEmpty
	private String About;
	


}
