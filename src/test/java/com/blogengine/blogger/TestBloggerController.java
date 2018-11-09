package com.blogengine.blogger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.blogengine.blogger.Blogger;
import com.blogengine.blogger.BloggerController;
import com.blogengine.blogger.BloggerService;

@RunWith(SpringRunner.class)
@WebMvcTest(BloggerController.class)
public class TestBloggerController {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BloggerService service;
	
	@Test
	public void testBloggerList() throws Exception {
		String URL = "/bloggers";
		
		Blogger blogger = new Blogger("Test","Test",(short)20,"TestUserName","valami@gmail.com");
		
		List<Blogger> allBlogger = Arrays.asList(blogger);
		
		when(service.getAllBlogger()).thenReturn(allBlogger);

		mvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].userName", equalTo("TestUserName")));	
		
	}

}

