package com.blogengine.blogger;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogengine.role.Role;
import com.blogengine.blogpost.BlogPostSaver;
import com.blogengine.validator.EmailValidator;

@Service
public class BloggerService implements UserDetailsService, BloggerSaver {

	private BloggerRepository bloggerRepository;
	private BlogPostSaver blogPostSaver;
	
	@Autowired
	public void setBr(BloggerRepository br) {
		this.bloggerRepository = br;
	}
		
	@Autowired
	public void setBlogPostSaver(BlogPostSaver blogPostSaver) {
		this.blogPostSaver = blogPostSaver;
	}

	public List<Blogger> getAllBlogger() {
		return bloggerRepository.findAll();
	}
	
	public Blogger findById(Long id){
		return bloggerRepository.findById(id)
				.filter(blogger -> blogger.getID() == id)
				.orElse(null);		
	}
	
	public Blogger findByUserName(String username) {
		return bloggerRepository.findFirst1ByUserName(username)
				.filter(blogger -> blogger.getUserName().equals((String)username))
				.orElse(null);
	}
	
	public boolean deleteById(String callerEmail, Long deletedBloggerID) {
		
		Optional<Blogger> caller = bloggerRepository.findByEmailAddress(callerEmail);
		Optional<Blogger> deleted = bloggerRepository.findById(deletedBloggerID);
		
		if(caller.isPresent() && deleted.isPresent()) {			
			// Ha a hívó superAdmin vagy ha a hívott fél nem admin
			if(caller.get().getRoles().contains(new Role("SUPER_ADMIN")) || !deleted.get().getRoles().contains(new Role("ADMIN"))) {
				bloggerRepository.deleteById(deletedBloggerID);
				return true;
			}			
		}
		return false;		
	}
	
	public Blogger save(ImperfectBlogger impBlogger) {
		
		Blogger blogger = new Blogger(impBlogger.getLastName(), impBlogger.getFirstName(),
				impBlogger.getAge(), impBlogger.getUserName(), impBlogger.getEmailAddress(), impBlogger.getPassword());
		
		Optional<Blogger> emailResult = bloggerRepository.findByEmailAddress(blogger.getEmailAddress());
		Optional<Blogger> userNameResult = bloggerRepository.findFirst1ByUserName(impBlogger.getUserName());

		if(emailResult.isPresent() || userNameResult.isPresent()) {
			return null;
		} 

		return bloggerRepository.save(blogger);
	}
	
	public Blogger findByEmail(String email) {		
		return bloggerRepository.findFirst1ByEmailAddress(email)
				.filter(blogger -> blogger.getEmailAddress().equals((String)email))
				.orElse(null);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Blogger bl = findByEmail(username);				
		return new UserDetailsImpl(bl);
	}

	@Override
	public Blogger save(Blogger blogger) {
		return bloggerRepository.save(blogger);
	}
	
	public Blogger edit(String callerEmail, ImperfectBlogger impBlogger) {
		
		Optional<Blogger> result = bloggerRepository.findByEmailAddress(callerEmail);
		
		if(result.isPresent()) {			
			result.get().setAge(impBlogger.getAge());
			result.get().setFirstName(impBlogger.getFirstName());
			result.get().setLastName(impBlogger.getLastName());
			
			if(impBlogger.getPassword().length() > 6)
				result.get().setPassword(impBlogger.getPassword());			
		}
		
		return bloggerRepository.save(result.get());
	}
	
	public boolean delete(String userEmail) {
		
		Optional<Blogger> result = bloggerRepository.findByEmailAddress(userEmail);
		
		if(result.isPresent()) {
			bloggerRepository.delete(result.get());
			
			result.get().getFollowedBlogPostsID().forEach(blogpostID -> 
				blogPostSaver.findById(blogpostID).getFollowersBloggerID().remove(result.get().getID()));
			
			return true;
		}		
		return false;
	}

	public boolean emailCheck(String email) {
		return EmailValidator.getInstance().emailValidate(email);
	}
}
