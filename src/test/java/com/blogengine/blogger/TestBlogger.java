package com.blogengine.blogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.blogengine.blogger.Blogger;
import com.blogengine.blogpost.BlogPost;

import static org.junit.Assert.*;

public class TestBlogger {

	private static Blogger test1;
	private static Blogger test2;

	@Before
	public void init() throws IllegalArgumentException {
		test1 = new Blogger("Kis","Pista",(short)20,"A","a@gmail.com","Ajelszo");
		test2 = new Blogger("Kis","Jeno",(short)20,"B","b@gmail.com","Bjelszo");
	}
	
	@Test
	public void testConstructor() throws IllegalArgumentException {
		Blogger newBlogger = new Blogger("Kis","Pista",(short)20,"A","a@gmail.com","Ajelszo");
		assertEquals(test1, newBlogger);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetUserName() {
		new Blogger("LastName","FirstName",(short)12,"","email@gmail.com","password");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAgeWithException() throws IllegalArgumentException {
		test1.setAge((short) 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEmailWithBadFormat() {
	    new Blogger("teszt","teszt",(short)12,"username","valami]gmail.","password");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEmailWithIllegalArgument() {
		new Blogger("teszt","teszt",(short)12,"username","","password");
	}
	
	@Test
	public void testBloggerEqual() {
		//Nem egyenlő Username & Email
        assertNotEquals(test1, test2);
		
		// Null összehasonlítás
        assertNotEquals(null, test1);
		
		// Ugyan azt az objektumot hasonlítjuk össze
        assertEquals(test1, test1);
		
		// Más osztálybeli objektumot hasonlítunk össze
        assertNotEquals(test1, 10);
		
		// Egyenlő email & nem egyenlő név
		Blogger t1 = new Blogger("teszt1","teszt1",(short)20,"teszt1","teszt@gmail.com","tesztpass");
		Blogger t2 = new Blogger("teszt2","teszt2",(short)20,"teszt2","teszt@gmail.com","tesztpass");
        assertEquals(t1, t2);

		// Egyenlő név & nem egyenlő email
        Blogger t3 = new Blogger("teszt3","teszt3",(short)20,"teszt2","mas@gmail.com","tesztpassz");
        assertEquals(t2, t3);
		
	}
	
	@Test
	public void testBlogPostCounter() throws IllegalArgumentException {
		assertEquals((long)0, (long)test1.getBlogPostCounter());
		test1.addBlogPost(new BlogPost(test1,"Valami cím", "Valami content"));
		assertEquals((long)1, (long)test1.getBlogPostCounter());
	}
	
	@Test
	public void testRegDate() {
		assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), test1.getRegDate());
	}

	@Test
	public void testLastActivityDate() {
		assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), test1.getLastActivityDate());
	}
}
