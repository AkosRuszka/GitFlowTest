package com.blogengine.blogpost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/blogpost")
@RestController
public class BlogPostController {

	BlogPostService bps;
	
	@Autowired
	public void setBps(BlogPostService bps) {
		this.bps = bps;
	}
	
	@GetMapping
	public List<BlogPost> list() {
		
		return bps.getByDateOrder();
	}
	
	@GetMapping(params="order")
	public List<BlogPost> getWithOrderDate(@RequestParam("order")String order) {
		return bps.getByDateOrder(order);
	}
	
	@GetMapping(params= "order", headers = "ordered=title")
	public List<BlogPost> getWithOrderTitle(@RequestParam("order")String order) {
		return bps.getByTitleOrder(order);
	}
	
	@GetMapping(params= {"title", "order"} , headers = "ordered=title")
	public List<BlogPost> get(@RequestParam("title")String title, @RequestParam("order")String order) {
		
		List<BlogPost> result = bps.findByTitleDetail(title,order);
		
		return result;
	}

	@GetMapping(value="/{id}")
	public BlogPost getById(@PathVariable("id")Long id) {
		return bps.findById(id);
	}
	
	@PostMapping
	public BlogPost newBP(@AuthenticationPrincipal final UserDetails user, @RequestBody String bp) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ImperfectBlogPost bl = null;
		
		try {			
			bl = objectMapper.readValue(bp, ImperfectBlogPost.class);			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bps.save(bl,user.getUsername()).get();
	}	
		
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public BlogPost edit(@AuthenticationPrincipal final UserDetails user, @RequestBody String change) {	
		
		List<String> roles = new ArrayList<>();
		user.getAuthorities().forEach(x -> roles.add(x.getAuthority()));
		
		ObjectMapper objectMapper = new ObjectMapper();
		ImperfectBlogPost bl = null;
		
		try {			
			bl = objectMapper.readValue(change, ImperfectBlogPost.class);			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bps.editBlogPost(bl, user.getUsername());	
	}
		
	@DeleteMapping(value="/delete/{id}")
	public void delete(@PathVariable("id")Long id) {
		bps.deleteById(id);
	}

	@PostMapping(value="/follow/{id}")
	public boolean follow(@AuthenticationPrincipal final UserDetails user, @PathVariable("id") Long blogpost_id) {
		return bps.follow(blogpost_id, user.getUsername());
	}
}
