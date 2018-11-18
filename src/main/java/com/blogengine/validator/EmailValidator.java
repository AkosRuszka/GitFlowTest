package com.blogengine.validator;

import java.util.regex.Pattern;

public class EmailValidator {
	private static EmailValidator instance;
	
	private static Pattern pattern;
	
	private final String PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private EmailValidator() { 
		pattern = Pattern.compile(PATTERN);
	}
	
	public static EmailValidator getInstance() {
		if(instance == null) {
			instance = new EmailValidator();
		}
		return instance;
	}
	
	public boolean emailValidate(String email) {
		
		return pattern.matcher(email).matches();

	}
	
}
