package com.blogengine.blogger;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Blogger findBloggerWithId(Long id) {

		/* 
		 * Ellenőrzi a Service hogy a visszakapott Bloggernek
		 * az ID-ja megfelel e a paraméterben kapott id-val.
		 * Ha megfelel visszaadja a Bloggert, ha nem felel meg akkor null-t ad vissza.
		 *  */
		return br.findById(id)
				.filter(blogger -> blogger.getID() == id)
				.orElse(null);		
	}
	
	public Blogger findBloggerWithEmail(String email) {
		
		/*
		 * A visszakapott Blogger-t ellenőrizzük filterrel, ha nem megy át a megfelelési teszten
		 * akkor a visszaadott érték egy null
		 * */
		return br.findFirst1ByEmailAddress(email)
				.filter(blogger -> blogger.getEmailAddress().equals((String)email))
				.orElse(null);
	}
	
	public Blogger findBloggerWithUserName(String username) {
		
		/*
		 * A blogger-t teszteljük a felhasználónevével is, ha ez megfelel akkor visszaadjuk azt
		 * ha nem felel meg akkor null-t adunk vissza.
		 *  */
		return br.findFirst1ByUserName(username)
				.filter(blogger -> blogger.getUserName().equals((String)username))
				.orElse(null);
	}

	public Blogger addBlogger(String lastName, String firstName, 
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
	
}
