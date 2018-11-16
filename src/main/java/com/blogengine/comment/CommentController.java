package com.blogengine.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping(value="/comment")
public class CommentController {
	
	private CommentService commentService;
	
	@Autowired
	public void setCs(CommentService cs) {
		this.commentService = cs;
	}

	@PostMapping
	public boolean newComment(@AuthenticationPrincipal final UserDetails user, @RequestBody String raw_comment) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ImperfectComment comment = null;
		
		try {
				
			comment = objectMapper.readValue(raw_comment, ImperfectComment.class);			
			return commentService.newComment(user.getUsername(),comment);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}	
	
	@PutMapping
	public boolean edit(@AuthenticationPrincipal final UserDetails user, @RequestBody String raw_comment) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ImperfectComment comment = null;
		
		try {
			
			comment = objectMapper.readValue(raw_comment, ImperfectComment.class);			
			return commentService.edit(user.getUsername(),comment);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
}
