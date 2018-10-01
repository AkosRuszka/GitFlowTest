package com.blogengine.domain;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
public class BlogPost {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@ManyToOne
	private Blogger author;
	
	private String title;
	private String content;
	private LocalDateTime posted; 
	
	@OneToMany(mappedBy="blog")
	private List<Comment> comments;
	
	public BlogPost() { /* empty for hibernate */ }
	
	public BlogPost(Blogger author, String title, String content) throws Exception {
		this.author = author;
		this.setTitle(title);
		this.setContent(content);
		this.posted = LocalDateTime.now();
		comments = new ArrayList<Comment>();
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

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a cím!");
		}
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) throws Exception{
		if(content.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a tartalom!");
		}
		this.content = content;
	}

	public LocalDateTime getDate() {
		return posted;
	}

	public void setDate(LocalDateTime date) {
		this.posted = date;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
}
