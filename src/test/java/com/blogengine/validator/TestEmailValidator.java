package com.blogengine.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestEmailValidator {
	
	private EmailValidator validator = EmailValidator.getInstance();
	
	
	private String[] valid = new String[] {
			"user@domain.com",
			"valami_sanyu@gmail.com",
			"skkd.valami@gmail.com",
			"klkld-valami@gmail.com",
			"klkld-valami@hunmail.com",
			"klkld-valami@gmail.hu",
			"disposable.style.email.withsymbol@example.com",
			"other.email-with-hyphen@example.com"
	};
	
	private String[] invalid = new String[] {
			"klkld-valami@gmail",
			"klkld-valami-gmail.hu",
			"klkld-valami-gmail.",
			"klkld-valami@gmail.",
			"A@b@c@example.com",
			"just\"not\"right@example.com",
			"this is\"not\\allowed@example.com",
			"john..doe@example.com"
	};
	
	
	@Test
	public void validEmailTest() {
		// Valid emailek tömbjéből fogja letesztelgetni az emailcímeket, és mindegyiknek meg kell felelnie!		
		for(String email: valid) {
			assertTrue(validator.emailValidate(email));
		}		
	}
	
	@Test
	public void invalidEmailTest() {
		// Invalid emailek tömbjéből fogja letesztelgetni az emailcímeket, és mindegyiknek el kell buknia!
		for(String email: invalid) {
			assertFalse(validator.emailValidate(email));
		}
	}
	
}
