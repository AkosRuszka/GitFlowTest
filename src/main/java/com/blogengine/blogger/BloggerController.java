package com.blogengine.blogger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/blogger")
@RestController
public class BloggerController {

	BloggerService bs;

	@Autowired
	public void setBs(BloggerService bs) {
		this.bs = bs;
	}
	
	@GetMapping(value="/list")
	public List<Blogger> list() {
		return bs.getAllBlogger();
	}
	
	@GetMapping(value="/profile")
	public Blogger myProfile(@AuthenticationPrincipal final UserDetails userDetails) {
		
		return bs.findByEmail(userDetails.getUsername());
	}	
	
	@GetMapping(value="/{id}")
	public Blogger findByID(@PathVariable("id")Long id) {		
		return bs.findById(id);
	}
	
	@PostMapping
	public Blogger newBlogger(@RequestBody Blogger newBlogger) {
		return bs.save(newBlogger);				
	}
	
	@PutMapping
	public Blogger edit() {
		return null;
	}
	
	@DeleteMapping(value="/profile")
	public void deleteProfile(@AuthenticationPrincipal final UserDetails userDetails) {
		
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@AuthenticationPrincipal final UserDetails userDetails , @PathVariable("id")Long id) {
		bs.deleteById(id);
	}
	
}
