package com.blogengine.validator;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestEmailValidator {
	
	private EmailValidator validator;
	
	@Parameterized.Parameter
	public JSONObject json;
	
	
	@Before
	public void init() {
		validator = EmailValidator.getInstance();
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection emails() {		
		return ParserHelper.data();
	}
		
	@Test
	public void emailValidate() {
		assertEquals(json.get("expected"),validator.emailValidate(json.get("email").toString()));
	}
		
}
