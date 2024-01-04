package com.Blog_Application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	String resourcename;
	String fieldName;
	long fieldValue;
	//String Exceptionhandlled;
	
	
	/*
	 * public ResourceNotFoundException(String resourcename, String fieldName, long
	 * fieldValue, String exceptionhandlled) {
	 * super(String.format("%s not found with this %s : %s %s",
	 * resourcename,fieldName,fieldValue,exceptionhandlled)); this.resourcename =
	 * resourcename; this.fieldName = fieldName; this.fieldValue = fieldValue;
	 * Exceptionhandlled = exceptionhandlled; }
	 */
	
	public ResourceNotFoundException(String resourcename, String fieldName, long fieldValue) {
		super(String.format("%s not found with this %s : %s", resourcename,fieldName,fieldValue));
		this.resourcename = resourcename;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
