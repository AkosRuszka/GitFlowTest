package com.blogengine.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogengine.Role;
import com.blogengine.blogger.Blogger;
import com.blogengine.blogger.BloggerSaver;
import com.blogengine.blogpost.BlogPost;
import com.blogengine.blogpost.BlogPostSaver;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private BlogPostSaver blogpostSaver;
	private BloggerSaver bloggerSaver;
	
	@Autowired
	public void setCr(CommentRepository cr) {
		this.commentRepository = cr;
	}
	
	@Autowired
	public void setBps(BlogPostSaver bps) {
		this.blogpostSaver = bps;
	}

	public boolean newComment(String blogger_email, ImperfectComment comment) throws Exception {
		BlogPost blogpost = blogpostSaver.findById(comment.getId());
		Blogger blogger = bloggerSaver.findByEmail(blogger_email);
		
		if(blogpost != null && !comment.getContent().isEmpty() && blogpost != null) {
			Comment new_comment = new Comment(blogger, blogpost, comment.getContent());
			commentRepository.save(new_comment);
			
			blogpost.addComment(new_comment);
			blogpostSaver.save(blogpost);
			
			blogger.addComment(new_comment);
			bloggerSaver.save(blogger);
			return true;
		}		
		return false;
	}

	public boolean edit(String blogger_email, ImperfectComment newComment) throws Exception {
		
		Blogger blogger = bloggerSaver.findByEmail(blogger_email);
		
		Optional<Comment> comment = commentRepository.findById(newComment.getId());
		
		String[] now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")).split(" ")[1].split(":");
		String[] comment_date = comment.get().getDate().split(" ")[1].split(":");
		
		// Ha a mostani idő és az eredeti komment idejének különbség kissebb mint 5 perc
		boolean active = ( now[0].equals(comment_date[0]) && 
				(Integer.parseInt(now[1])-Integer.parseInt(comment_date[1]) < 5));
		
		if(comment.isPresent() && !newComment.getContent().isEmpty() && 
				( (blogger.equals(comment.get().getAuthor()) && active ) || blogger.getRoles().contains(new Role("ADMIN")) )) {
			
			comment.get().setContent(newComment.getContent());
			
			// akkor moderálva lett, ezt jelezzük a kommentben!
			if(!blogger.equals(comment.get().getAuthor())) {				
				comment.get().moderated();
				comment.get().setModerator(blogger.getUserName());				
			}

			commentRepository.save(comment.get());
				
			return true;
				
		}
		return false;
	}
}
