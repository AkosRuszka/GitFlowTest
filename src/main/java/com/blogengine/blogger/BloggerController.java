package com.blogengine.blogger;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BloggerController {

	BloggerService bs;

	@Autowired
	public void setBs(BloggerService bs) {
		this.bs = bs;
	}
	
	@GetMapping(value="/bloggers")	
	public List<Blogger> bloggerIndex() {
		return bs.getAllBlogger();
	}
	
	@PostMapping(value="/bloggers")
	public Blogger newBlogger(@RequestBody Blogger newBlogger) {
		return bs.save(newBlogger);				
	}
	
	@GetMapping(value="/blogger/{email}", headers="com=email")
	public Blogger bloggerListWithEmail(@PathVariable("email")String email) {		
		return bs.findBloggerWithEmail(email);		
	}	
	
	@GetMapping(value="/blogger/{username}", headers="com=username")
	public Blogger bloggerListWithUserName(@PathVariable("username")String username) {		
		return bs.findBloggerWithUserName(username);		
	}
	
	@GetMapping(value="/blogger/{id}", headers="com=id")
	public Blogger bloggetListWithID(@PathVariable("id")Long id) {		
		return bs.findBloggerWithId(id);
	}
	
	@DeleteMapping(value="/blogger/{id}")
	public void deleteBlogger(@PathVariable("id")Long id) {
		bs.deleteById(id);
	}
	
}
