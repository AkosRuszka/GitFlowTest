package com.blogengine.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.blogengine.blogger.Blogger;
import com.blogengine.blogpost.BlogPost;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Comment {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long ID;
	
	@ManyToOne
	private Blogger author;
	
	@JsonBackReference
	@ManyToOne
	private BlogPost blog;
	
	@Column(length=1000)
	private String content;
	
	@Column(length=10)
	private String date;
	
	private boolean moderated;
	private String moderator;
	
	protected Comment() { /* empty for hibernate */ }
	
	public Comment(Blogger author, BlogPost blog, String content) throws Exception {
		this.author = author;
		this.blog = blog;
		this.setContent(content);
		date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
	}
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
	public String getModerator() {
		return moderator;
	}

	public void setModerator(String moderator) {
		this.moderator = moderator;
	}

	public boolean isModerated() {
		return moderated;
	}

	public void setModerated(boolean moderated) {
		this.moderated = moderated;
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
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public void moderated() {
		moderated = true;
	}
}
