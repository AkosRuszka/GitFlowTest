package com.blogengine.blogpost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {

	private BlogPostRepository bpr;

	@Autowired
	public void setBpr(BlogPostRepository bpr) {
		this.bpr = bpr;
	}
	
	public List<BlogPost> findBlogPostByTitleDetail(String title, String... order) {		
		
		List<BlogPost> result = bpr.findByTitle(title); 
		
		if(order.length == 0) return result;
		
		return titledListOrder(result, order[0]);	
	}
	
	public BlogPost findBlogPostsById(Long id) {
		return bpr.findById(id)
				.filter(blogpost -> blogpost.getId().equals(id))
				.orElse(null);
	}

	public BlogPost save(BlogPost bp) {
		return bpr.save(bp);
	}

	public void deleteById(Long id) {
		bpr.deleteById(id);		
	}

	public List<BlogPost> getBlogPostsByDateOrder(String... order) {
		if(order.length == 0) {
			return bpr.findAll();
		}
		
		switch(order[0]) {
		case "asc":
			return bpr.findAll().stream().sorted((x,y)->x.getPostedDate().compareTo(y.getPostedDate())).collect(Collectors.toList());
		case "desc":
			return bpr.findAll().stream().sorted((x,y)->y.getPostedDate().compareTo(x.getPostedDate())).collect(Collectors.toList());
		default:
			return bpr.findAll();
		}
	}
	
	public List<BlogPost> getBlogPostsByTitleOrder(String... order) {
		
		List<BlogPost> result = bpr.findAll();
		
		if(order.length == 0) {
			return result;
		} else {
			return titledListOrder(result, order[0]);
		}
	}
	
	private List<BlogPost> titledListOrder(List<BlogPost> list, String order) {
		
		switch(order) {
		case "asc":
			return list.stream().sorted((x,y)->x.getTitle().compareTo(y.getTitle())).collect(Collectors.toList());
		case "desc":
			return list.stream().sorted((x,y)->y.getTitle().compareTo(x.getTitle())).collect(Collectors.toList());
		default:
			return list;
		}
		
	}
}
