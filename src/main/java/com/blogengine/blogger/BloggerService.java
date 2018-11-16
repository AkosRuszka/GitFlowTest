package com.blogengine.blogger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BloggerService implements UserDetailsService, BloggerSaver {

	private BloggerRepository br;

	@Autowired
	public void setBr(BloggerRepository br) {
		this.br = br;
	}
	
	public List<Blogger> getAllBlogger() {
		return br.findAll();
	}
	
	public Blogger findById(Long id) {
		return br.findById(id)
				.filter(blogger -> blogger.getID() == id)
				.orElse(null);		
	}
	
	public Blogger findByUserName(String username) {
		return br.findFirst1ByUserName(username)
				.filter(blogger -> blogger.getUserName().equals((String)username))
				.orElse(null);
	}

	public Blogger add(String lastName, String firstName, 
			short age, String userName, String emailAddress) throws IllegalArgumentException{

		Blogger bl = new Blogger(lastName, firstName, age, userName, emailAddress);
		
		br.save(bl);
		
		return bl;		
	}
	
	public void deleteById(Long id) {			
		br.deleteById(id);
	}
	
	public Blogger save(Blogger bl) {
		return br.save(bl);
	}
	
	public Blogger findByEmail(String email) {		
		return br.findFirst1ByEmailAddress(email)
				.filter(blogger -> blogger.getEmailAddress().equals((String)email))
				.orElse(null);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Blogger bl = findByEmail(username);				
		return new UserDetailsImpl(bl);
	}
}
