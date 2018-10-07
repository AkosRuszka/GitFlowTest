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
		return bpr.findAll();
	}
	
	public BlogPost findBlogPostByTitle(String title) {
		return bpr.findFirst1ByTitle(title)
				.filter(blogpost -> blogpost.getTitle().equals((String)title))
				.orElse(null);
	}
	
	public List<BlogPost> findBlogPostsByPostedDateASC(String date) {
		return bpr.findByPostedDateOrderByPostedDateAsc(date);
	}
	
	public List<BlogPost> findBlogPostsByPostedDateDESC(String date) {
		return bpr.findByPostedDateOrderByPostedDateDesc(date);
	}
	
	public List<BlogPost> findBlogPostsByPostedDateDef(String date) {
		return bpr.findByPostedDate(date);
	}
	
	public BlogPost findBlogPostsById(Long id) {
		return bpr.findById(id)
				.filter(blogpost -> blogpost.getId().equals(id))
				.orElse(null);
	}
}
