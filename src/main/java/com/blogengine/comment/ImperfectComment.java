package com.blogengine.comment;

public class ImperfectComment {

	private Long id;
	private String content;
	
	public ImperfectComment(Long id, String cont) {
		this.id = id;
		content = cont;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
