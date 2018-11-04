package com.blogengine.comment;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.blogengine.blogger.Blogger;
import com.blogengine.blogpost.BlogPost;
import com.blogengine.comment.Comment;

public class TestComment {
	Comment cm = new Comment();
	
	@Test
	public void testConstructor() throws Exception{
		Blogger bl = new Blogger();
		BlogPost bp = new BlogPost();
		cm = new Comment(bl,bp,"Comment");
		
		assertEquals(bl, cm.getAuthor());
		assertEquals(bp, cm.getBlog());
		
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		assertEquals(true, time.equals(cm.getDate()));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetContentWithException() throws Exception{
		cm.setContent("");
	}
}
