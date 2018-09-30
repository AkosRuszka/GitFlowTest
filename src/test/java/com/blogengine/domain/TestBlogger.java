package com.blogengine.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestBlogger {

	private Blogger bl = new Blogger();
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLastNameException() throws Exception {
		bl.setLastName("");
	}
	
	@Test
	public void testSetLastName() throws Exception {
		bl.setLastName("Pacsma");
		assertEquals("Pacsma", bl.getLastName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAgeException() throws Exception {
		bl.setAge((short) 2);
	}
	
	@Test
	public void testSetAge() throws Exception {
		bl.setAge((short)30);
		assertEquals((short)30, bl.getAge());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEmailException() {
		bl.setEmailAddress("valami@gmail.");
	}
	
}
