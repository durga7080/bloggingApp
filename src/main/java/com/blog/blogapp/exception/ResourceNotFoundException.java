package com.blog.blogapp.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{

	String resourcename;
	String fieldName;
	long fieldvalue;
	String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ResourceNotFoundException(String resourcename, String fieldName, Long fieldvalue) {
		super(String.format("%s not found with %s : %s",resourcename,fieldName,fieldvalue));
		this.resourcename = resourcename;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
	
	public ResourceNotFoundException(String resourcename, String fieldName, String email) {
		super(String.format("%s not found with %s : %s",resourcename,fieldName,email));
		this.resourcename = resourcename;
		this.fieldName = fieldName;
		this.email = email;
	}
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	
	
	
	 
}
