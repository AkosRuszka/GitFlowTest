package com.blogengine.blogpost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogPostController {

	BlogPostService bps;

	@Autowired
	public void setBps(BlogPostService bps) {
		this.bps = bps;
	}
	
	@GetMapping(value="/blogposts")
	public List<BlogPost> blogPostList() {
		return bps.getBlogPostsByDateOrder();
	}
	
	@GetMapping(value="/blogposts/{id}")
	public BlogPost getById(@PathVariable("id")Long id) {
		return bps.findBlogPostsById(id);
	}
	
	@GetMapping(value="/blogposts", params="order")
	public List<BlogPost> getBlogPostsWithOrderDate(@RequestParam("order")String order) {
		// order-nek megfelelő rendezéssel adja vissza a listákat.
		return bps.getBlogPostsByDateOrder(order);
	}
	
	@GetMapping(value="/blogposts", params= {"title", "order"} , headers = "ordered=title")
	public List<BlogPost> getBlogPosts(@RequestParam("title")String title, @RequestParam("order")String order) {
		
		List<BlogPost> result = bps.findBlogPostByTitleDetail(title,order);
		
		return result;
	}
	
	@GetMapping(value="/blogposts", params= "order", headers = "ordered=title")
	public List<BlogPost> getBlogPostsWithOrderTitle(@RequestParam("order")String order)
	{
		return bps.getBlogPostsByTitleOrder(order);
	}
	
	@PostMapping(value="/blogposts")
	public BlogPost newBlogPost(@RequestBody BlogPost bp) {
		return bps.save(bp);
	}
	
	@DeleteMapping(value="/blogposts/{id}")
	public void deleteBlogPost(@PathVariable("id")Long id) {
		bps.deleteById(id);
	}
	
}
