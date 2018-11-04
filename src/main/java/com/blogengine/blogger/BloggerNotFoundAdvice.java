package com.blogengine.blogger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BloggerNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(BloggerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String bloggerNotFoundHandler(BloggerNotFoundException ex) {
		return ex.getMessage();
	}
	
}
