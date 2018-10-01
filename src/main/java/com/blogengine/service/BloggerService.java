package com.blogengine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogengine.domain.Blogger;
import com.blogengine.repository.BloggerRepository;

@Service
public class BloggerService {

	private BloggerRepository br;

	@Autowired
	public void setBr(BloggerRepository br) {
		this.br = br;
	}
	
	public List<Blogger> getAllBlogger() {
		return br.findAll();
	}
	
	public Optional<Blogger> findBloggerWithId(Long id) {
		/* Lekezelendő!!! */
		return br.findById(id);
	}
	
	public Optional<Blogger> findBloggerWithEmail(String email) {
		/* Lekezelendő!!! */
		return br.findFirst1ByEmailAddress(email);
	}
	
	public Optional<Blogger> findBloggerWithUserName(String username) {
		/* Lekezelendő */
		return br.findFirst1ByUserName(username);
	}
	
}
