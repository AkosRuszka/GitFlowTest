package com.blogengine.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogengine.domain.BlogPost;
import com.blogengine.service.BlogPostService;

@RestController
public class BlogPostController {

	BlogPostService bps;

	@Autowired
	public void setBps(BlogPostService bps) {
		this.bps = bps;
	}
	
	@RequestMapping(
			value="/blogposts",
			method=RequestMethod.GET)
	public List<BlogPost> blogPostList() {
		return bps.getBlogPosts();
	}
	
	@RequestMapping(
			value="/blogposts",
			params="title",
			method=RequestMethod.GET)
	public BlogPost getByTitle(@RequestParam("title")String title) {
		/* Lekezelés!!!! */
		return bps.findBlogPostByTitle(title).get();
	}
	
	@RequestMapping(
			value="/blogposts",
			params= {"date", "order"},
			method=RequestMethod.GET)
	public List<BlogPost> getByDate(
			@RequestParam("date")String date,
			@RequestParam("order")String order) {
		if(order.equals("def")) {
			return bps.findBlogPostsByPostedDateDef(date);
		}
		if(order.equals("asc")) {
			return bps.findBlogPostsByPostedDateASC(date);
		}
		return bps.findBlogPostsByPostedDateDESC(date);
	}
	
	@RequestMapping(
			value="/blogpost",
			params="id",
			method=RequestMethod.GET)
	public BlogPost getById(@RequestParam("id")Long id) {
		return bps.findBlogPostsById(id).get();
	}
	
}
