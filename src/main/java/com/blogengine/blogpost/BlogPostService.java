package com.blogengine.blogpost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogengine.Role;
import com.blogengine.blogger.Blogger;
import com.blogengine.blogger.BloggerSaver;

@Service
public class BlogPostService implements BlogPostSaver{

	private BlogPostRepository blogpostReposiroty;
	private BloggerSaver bloggerSaver;
	
	@Autowired
	public void setBpr(BlogPostRepository bpr) {
		this.blogpostReposiroty = bpr;
	}
	
	@Autowired
	public void setBr(BloggerSaver br) {
		this.bloggerSaver = br;
	}
	
	/** BlogPosts of same title with order<b>
	 * @param title
	 * @param order - 0 or 1 element
	 * <b>values:</b> 
	 * <p>null - list will not be sorted
	 * <p>asc -  list will be ascending sorted
	 * <p>desc - list will be descending sorted
	 * **/
	public List<BlogPost> findByTitleDetail(String title, String... order) {		
		
		List<BlogPost> result = blogpostReposiroty.findByTitle(title); 
		
		if(order.length == 0) return result;
		
		return titledListOrder(result, order[0]);	
	}
	
	public BlogPost findById(Long id) {
		return blogpostReposiroty.findById(id)
				.filter(blogpost -> blogpost.getId().equals(id))
				.orElse(null);
	}

	public Optional<BlogPost> save(ImperfectBlogPost imbp, String blogger_email) {
		
		Optional<BlogPost> result = blogpostReposiroty.findFirst1ByTitle(imbp.getTitle());
	
		BlogPost bp = new BlogPost(bloggerSaver.findByEmail(blogger_email), imbp.getTitle(), imbp.getContent());
		
		if(result.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.of(blogpostReposiroty.save(bp));
		}
	}
	
	public BlogPost save(BlogPost bp) {
		return blogpostReposiroty.save(bp);
	}

	public boolean follow(Long blogpost_id, String user_email) {
		
		Optional<BlogPost> bp = blogpostReposiroty.findById(blogpost_id);
		
		Blogger blogger = bloggerSaver.findByEmail(user_email);
		
		// MEgnézi hogy fel van e már rá iratkozva
		if(bp.isPresent()) {
			List<Long> followers_id = bp.get().getFollowers();
			
			if(followers_id.contains(blogger.getID())) {
				return false;
			} else {
				bp.get().addFollower(blogger.getID());
				blogpostReposiroty.save(bp.get());
				
				blogger.addFollowedBlogPost(blogpost_id);
				bloggerSaver.save(blogger);
			}
		}
		return true;
	}
	
	public void deleteById(Long id) {
				
		findById(id).getFollowers().forEach(blogger_id -> {
			bloggerSaver.findById(blogger_id).getFollowedBlogPostsID().remove(id);			
		});
		
		blogpostReposiroty.deleteById(id);		
	}

	/** Sorted list by date<p>
	 * <b>values:</b>
	 * <p>empty parameter	- if you don't want sort the list
	 * <p>asc		- if you want sort the list with ascending order
	 * <p>desc 	- if you want sort the list with descending oder
	 * @param order
	 * **/
	public List<BlogPost> getByDateOrder(String... order) {
		if(order.length == 0) {
			return blogpostReposiroty.findAll();
		}
		
		switch(order[0]) {
		case "asc":
			return blogpostReposiroty.findAll().stream().sorted((x,y)->x.getPostedDate().compareTo(y.getPostedDate())).collect(Collectors.toList());
		case "desc":
			return blogpostReposiroty.findAll().stream().sorted((x,y)->y.getPostedDate().compareTo(x.getPostedDate())).collect(Collectors.toList());
		default:
			return blogpostReposiroty.findAll();
		}
	}
	
	public List<BlogPost> getByTitleOrder(String... order) {
		
		List<BlogPost> result = blogpostReposiroty.findAll();
		
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
	
	public BlogPost editBlogPost(ImperfectBlogPost change, String blogger_email) {
		
		BlogPost blogpost = findById(change.getId());
		Blogger blogger = bloggerSaver.findByEmail(blogger_email);		
		
		if(blogpost != null && blogger != null &&
			( blogpost.getAuthor().equals(blogger) || blogger.getRoles().contains(new Role("ADMIN"))	)) {
			
			blogpost.setTitle(change.getTitle());
			blogpost.setContent(change.getContent());
			return blogpostReposiroty.save(blogpost);
		}

		return null;		
	}
}












