package com.blogengine.domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class TestBlogPost {
	BlogPost bp = new BlogPost();
	
	@Test
	public void testConstuctor() throws Exception {
		Blogger bl = new Blogger();
		bp = new BlogPost(bl,"Cim1","Content1");
		assertEquals("Cim1", bp.getTitle());
		assertEquals("Content1", bp.getContent());
		assertEquals(0, bp.getComments().size());
		
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		assertEquals(time, bp.getPostedDate());
		
		assertEquals(bl, bp.getAuthor());
	}
	
	@Test
	public void testAddComment() {
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetTitleWithException() throws Exception {
		bp.setTitle("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetContentWithException() throws Exception {
		bp.setContent("");
	}
}
