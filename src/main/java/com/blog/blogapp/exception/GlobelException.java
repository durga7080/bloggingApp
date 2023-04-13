package com.blog.blogapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blogapp.payload.ApiResponce;

@RestControllerAdvice
public class GlobelException {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourseNotFound(ResourceNotFoundException m)
	{
		String message=m.getMessage();
		ApiResponce apiResponce =new ApiResponce(message,false);
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BadCredintial.class)
	public ResponseEntity<ApiResponce> badCredential(BadCredintial n)
	{
	
		String message=n.getMessage();
		ApiResponce apiResponce =new ApiResponce(message,false);
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String ,String>>handlemethodArgsNotValidExcep(MethodArgumentNotValidException e)
	{
		Map<String, String > resp = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->
		{
			String filedName= ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(filedName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

}
