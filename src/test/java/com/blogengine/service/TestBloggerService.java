package com.blogengine.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.blogengine.domain.Blogger;
import com.blogengine.repository.BloggerRepository;

public class TestBloggerService {

	private BloggerService bs = new BloggerService();
	
	private BloggerRepository br = Mockito.mock(BloggerRepository.class);
	
	@Before
	public void init() {
		bs.setBr(br);
	}
	
	@Test
	public void valami() {
		
		when(br.findAll()).thenReturn(new ArrayList<Blogger>());
		
		assertEquals(true, bs.getAllBlogger().isEmpty());
	}
	
	
}
