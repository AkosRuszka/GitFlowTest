package com.blogengine.blogpost;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.blogengine.blogger.Blogger;
import com.blogengine.comment.Comment;

@Entity
public class BlogPost {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long ID;
	
	@ManyToOne
	private Blogger author;
	
	@Column(length=50)
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	@Column(length=10)
	private String postedDate; 
	
	@OneToMany(mappedBy="blog")
	private List<Comment> comments;
	
	@ElementCollection
	@CollectionTable(name="Followers", joinColumns= @JoinColumn(name="ID"))
	private List<Long> followersBloggerID;
	
	public BlogPost() { /* empty for hibernate */ }
	
	public BlogPost(Blogger author, String title, String content) throws IllegalArgumentException {
		this.author = author;
		this.setTitle(title);
		this.setContent(content);
		this.postedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
		comments = new ArrayList<>();
		followersBloggerID = new ArrayList<>();
	}
	
	public Long getId() {
		return ID;
	}

	public void setId(Long id) {
		this.ID = id;
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
	public void setTitle(String title) throws IllegalArgumentException{
		if(title.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a cím!");
		}
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) throws IllegalArgumentException{
		if(content.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a tartalom!");
		}
		this.content = content;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String date) {
		this.postedDate = date;
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

	public List<Long> getFollowersBloggerID() {
		return followersBloggerID;
	}

	public void setFollowersBloggerID(List<Long> followersBloggerID) {
		this.followersBloggerID = followersBloggerID;
	}
	
	public List<Long> getFollowers() {
		return this.followersBloggerID;
	}

	public void addFollower(Long blogger) {
		this.followersBloggerID.add(blogger);
	}
}
