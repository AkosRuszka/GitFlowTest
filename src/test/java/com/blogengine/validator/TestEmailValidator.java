package com.blogengine.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestEmailValidator {
	
	private EmailValidator validator = EmailValidator.getInstance();
	
	
	private String[] valid = new String[] {
			"user@domain.com",
			"valami_sanyu@gmail.com",
			"skkd.valami@gmail.com",
			"klkld-valami@gmail.com",
			"klkld-valami@hunmail.com",
			"klkld-valami@gmail.hu"	
	};
	
	private String[] invalid = new String[] {
			"klkld-valami@gmail",
			"klkld-valami-gmail.hu",
			"klkld-valami-gmail.",
			"klkld-valami@gmail."
	};
	
	
	@Test
	public void validEmailTest() {
		boolean val = true;
		
		for(String email: valid) {
			val = val && validator.emailValidate(email);
		}
		
		assertEquals(true,val);
	}
	
	@Test
	public void invalidEmailTest() {
		boolean val = true;
		
		for(String email: invalid) {
			val = val && validator.emailValidate(email);
		}
		
		assertEquals(false,val);
		
	}
	
}
