package com.blogengine.blogger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.blogengine.blogger.Blogger;
import com.blogengine.blogger.BloggerRepository;
import com.blogengine.blogger.BloggerService;

public class TestBloggerService {

	private BloggerService bs = new BloggerService();
	private BloggerRepository br = Mockito.mock(BloggerRepository.class);
	Blogger test;
	
	@Before
	public void init() {
		bs.setBr(br);
		try {
			test = new Blogger("Test","Test",(short)20,"Test","test@gmail.com","tesztjelszo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetAllBlogger() {
		
		when(br.findAll()).thenReturn(new ArrayList<Blogger>());	
		assertEquals(true, bs.getAllBlogger().isEmpty());
	}
	
	@Test
	public void testfindBloggerWithID() throws Exception {

		test.setID((long)10);
		
		/* Első megközelítés, a br rossz ID-s Bloggert ad vissza
		 * A bs teszteli a filterrel az ID-t, null-t várunk, mivel az ID nem megfelelő 
		 * */
		when(br.findById((long)5)).thenReturn(Optional.of(test));
		assertEquals(null, bs.findById((long)5));
		
		
		/* Második megközelítés, a br jó ID-s Bloggert ad vissza		
		 * Az ID megfelelő, a filter elfogadja, visszaadja a Bloggert 
		 * */
		when(br.findById((long)10)).thenReturn(Optional.of(test));
		assertEquals(true, bs.findById((long)10).equals((Blogger)test));		
	}
	
	@Test
	public void testfindBloggerWithUserName() throws Exception {
		
		/* Mivel a név a visszaadott objektumnál is megegyezik, ezért visszaadja
		 * a br-től kapott Bloggert
		 * */
		when(br.findFirst1ByUserName("Test")).thenReturn(Optional.of(test));
		assertEquals(true, test.equals((Blogger)bs.findByUserName("Test")));
		
		/* Itt a br a paraméterben szereplő névnek nem megfelelő
		 * Objektummal tér vissza, így null-t kell visszaadnia a filter miatt
		 *  */
		when(br.findFirst1ByUserName("Testt")).thenReturn(Optional.of(test));
		assertEquals(null, bs.findByUserName("Testt"));
		
	}
	
	
	
	
}
