package com.blogengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogengine.domain.Blogger;
import com.blogengine.service.BloggerService;

@RestController
public class BloggerController {

	BloggerService bs;

	@Autowired
	public void setBs(BloggerService bs) {
		this.bs = bs;
	}
	
	@RequestMapping("/bloggers")
	public List<Blogger> bloggersList() {
		return bs.getAllBlogger();
	}
	
	@RequestMapping("/blogger-email/{email}")
	public Blogger bloggerListWithEmail(@PathVariable("email")String email) {
		return bs.findBloggerWithEmail(email);
	}	
	
	@RequestMapping("/blogger-name/{username}")
	public Blogger bloggerListWithUserName(@PathVariable("username")String username) {
		return bs.findBloggerWithUserName(username);
	}
	
	
	
}
