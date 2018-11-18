package com.blogengine.error;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BloggerNotFoundAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BloggerException.class)
	public ResponseEntity<ErrorDetails> bloggerNotFoundHandler(BloggerException ex, WebRequest request) {		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false)));
	}
	
}
