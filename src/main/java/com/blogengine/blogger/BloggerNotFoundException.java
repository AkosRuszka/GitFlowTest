package com.blogengine.blogger;

public class BloggerNotFoundException extends RuntimeException {

	public BloggerNotFoundException(Long id) {
		super("Not found Blogger with "+ id +" id");
	}
}
