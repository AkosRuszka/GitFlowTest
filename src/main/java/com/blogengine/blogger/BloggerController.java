package com.blogengine.blogger;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.blogengine.error.BloggerException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/blogger")
@RestController
public class BloggerController {

	private BloggerService bs;

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
	public ResponseEntity<Blogger> findByID(@PathVariable("id")Long id) throws BloggerException {		
		
		Blogger result = bs.findById(id);
		
		if(result == null) {
			throw new BloggerException("Nem találtuk a kért ID-val ellátott bloggert: " + id);
		} 
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity newBlogger(@RequestBody String rawBlogger) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ImperfectBlogger impBlogger = null;
		
		try {			
			impBlogger = objectMapper.readValue(rawBlogger, ImperfectBlogger.class);	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Blogger result = bs.save(impBlogger);
		if(result == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ezzel az email címmel már létezik felhasználó");
		}		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bs.save(impBlogger));				
	}
	
	@GetMapping("/check")
	public ResponseEntity<String> emailCheck(@RequestBody String email) {
		if(bs.emailCheck(email)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("");		
	}
	
	@PutMapping
	public Blogger edit(@AuthenticationPrincipal final UserDetails userDetail, @RequestBody String rawBloggerData) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ImperfectBlogger impBlogger = null;
		
		try {			
			impBlogger = objectMapper.readValue(rawBloggerData, ImperfectBlogger.class);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bs.edit(userDetail.getUsername(), impBlogger);
	}
	
	@DeleteMapping(value="/profile")
	public boolean deleteProfile(@AuthenticationPrincipal final UserDetails userDetails) {
		return bs.delete(userDetails.getUsername());
	}
	
	@DeleteMapping(value="/{id}")
	public boolean delete(@AuthenticationPrincipal final UserDetails userDetails , @PathVariable("id")Long id) {
		return bs.deleteById(userDetails.getUsername(), id);
	}
	
}
