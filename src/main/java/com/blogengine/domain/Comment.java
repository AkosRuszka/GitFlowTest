package com.blogengine.domain;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
public class Comment {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@ManyToOne
	private Blogger author;
	
	@ManyToOne
	private BlogPost blog;
	
	private String content;
	private LocalDateTime date;
	
	public Comment() { /* empty for hibernate */ }
	
	public Comment(Blogger author, BlogPost blog, String content) throws Exception {
		this.author = author;
		this.blog = blog;
		this.setContent(content);
		date = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Blogger getAuthor() {
		return author;
	}
	public void setAuthor(Blogger author) {
		this.author = author;
	}
	
	public BlogPost getBlog() {
		return blog;
	}
	public void setBlog(BlogPost blog) {
		this.blog = blog;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) throws Exception{
		if(content.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a comment!");
		}
		this.content = content;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
