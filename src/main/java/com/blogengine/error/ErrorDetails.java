package com.blogengine.error;

import java.time.LocalDate;

public class ErrorDetails {
	private LocalDate timestamp;
	private String message;
	private String details;

	public ErrorDetails(LocalDate timestamp, String message, String details) {
		super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	}	
}
