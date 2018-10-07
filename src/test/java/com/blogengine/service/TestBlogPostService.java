package com.blogengine.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.blogengine.domain.BlogPost;
import com.blogengine.domain.Blogger;
import com.blogengine.repository.BlogPostRepository;

public class TestBlogPostService {

	private BlogPostService bps = new BlogPostService();
	private BlogPostRepository br = Mockito.mock(BlogPostRepository.class);
	
	@Before
	public void init() {
		bps.setBpr(br);
	}
	
	@Test
	public void testFindBlogPostByTitle() throws Exception {
				
		BlogPost testBlogPost = new BlogPost(null,"Valami","Valami C");
		
		/* A br jól működik, jó eredményt ad vissza így a bps elfogadja */
		when(br.findFirst1ByTitle("Valami")).thenReturn(Optional.of(testBlogPost));		
		assertEquals(true, bps.findBlogPostByTitle("Valami").getTitle().equals(((BlogPost)testBlogPost).getTitle()));
		
		/* A br nem jól működik, nem adott vissza jó eredményt de a bps lekezeli
		 * a rossz eredményt nem továbbítja, a filter jól működik.
		 * */
		when(br.findFirst1ByTitle("Valami2")).thenReturn(Optional.of(testBlogPost));
		assertEquals(null, bps.findBlogPostByTitle("Valami2"));
		
	}

}
