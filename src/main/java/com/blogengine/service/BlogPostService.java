package com.blogengine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogengine.domain.BlogPost;
import com.blogengine.repository.BlogPostRepository;

@Service
public class BlogPostService {

	private BlogPostRepository bpr;

	@Autowired
	public void setBpr(BlogPostRepository bpr) {
		this.bpr = bpr;
	}
	
	public List<BlogPost> getBlogPosts() {
		/* null kezelés kell */
		return bpr.findAll();
	}
	
	public Optional<BlogPost> findBlogPostByTitle(String title) {
		/* null kezelés kell */
		return bpr.findFirst1ByTitle(title);
	}
	
	public List<BlogPost> findBlogPostsByPostedDateASC(String date) {
		/* null kezelés kell */
		return bpr.findByPostedDateOrderByPostedDateAsc(date);
	}
	
	public List<BlogPost> findBlogPostsByPostedDateDESC(String date) {
		/* null kezelés kell */
		return bpr.findByPostedDateOrderByPostedDateDesc(date);
	}
	
	public List<BlogPost> findBlogPostsByPostedDateDef(String date) {
		return bpr.findByPostedDate(date);
	}
}
