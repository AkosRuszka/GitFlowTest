package com.blogengine.domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class TestBlogger {

	private static Blogger test1;
	private static Blogger test2;
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLastNameWithException() throws Exception {
		test1.setLastName("");
	}
	
	@Before
	public void init() throws Exception {
		test1 = new Blogger("Kis","Pista",(short)20,"A","a@gmail.com");
		test2 = new Blogger("Kis","Jeno",(short)20,"B","b@gmail.com");
	}
	
	@Test
	public void testConstructor() throws Exception {
		Blogger newBlogger = new Blogger("Kis","Pista",(short)20,"A","a@gmail.com");
		assertEquals(test1, newBlogger);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFirstName() throws Exception {
		test1.setFirstName("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetUserName() {
		test1.setUserName("");
	}
	
	@Test
	public void testSetLastName() throws Exception {
		test1.setLastName("Pacsma");
		assertEquals("LastNameSetter ellenőrzés","Pacsma", test1.getLastName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAgeWithException() throws Exception {
		test1.setAge((short) 2);
	}
	
	@Test
	public void testSetAge() throws Exception {
		test1.setAge((short)30);
		assertEquals((short)30, test1.getAge());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEmailWithBadFormat() {
		test1.setEmailAddress("valami@gmail.");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEmailWithIllegalArgument() {
		test1.setEmailAddress("");
	}
	
	@Test
	public void testBloggerEqual() {
		//Nem egyenlő Username & Email
		assertEquals(false,test1.equals(test2));
		
		// Null összehasonlítás
		assertEquals(false,test1.equals(null));
		
		// Ugyan azt az objektumot hasonlítjuk össze
		assertEquals(true,test1.equals(test1));
		
		// Más osztálybeli objektumot hasonlítunk össze
		assertEquals(false,test1.equals(new Integer(10)));
		
		// Egyenlő email & nem egyenlő név
		String oldemail = test1.getEmailAddress();
		test1.setEmailAddress(test2.getEmailAddress());
		assertEquals(true,test1.equals(test2));

		// Egyenlő név & nem egyenlő email
		test1.setEmailAddress(oldemail); // email visszaállíátsa
		// Név átállítása 
		test1.setUserName(test2.getUserName());
		assertEquals(true,test1.equals(test2));
		
	}
	
	@Test
	public void testBlogPostCounter() throws Exception {
		assertEquals((long)0, (long)test1.getBlogPostCounter());
		test1.addBlogPost("Valami cím", "Valami content");
		assertEquals((long)1, (long)test1.getBlogPostCounter());
	}
	
	@Test
	public void testRegDate() {
		assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), test1.getRegDate());
	}
	
	public void testLastActivityDate() {
		assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), test1.getLastActivityDate());
	}
}
