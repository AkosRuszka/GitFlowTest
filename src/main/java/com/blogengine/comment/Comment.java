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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Comment {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Getter @Setter private Long ID;
	
	@ManyToOne
    @Getter @Setter private Blogger author;
	
	@JsonBackReference
	@ManyToOne
    @Getter @Setter private BlogPost blog;
	
	@Column(length=1000)
	@Getter private String content;
	
	@Column(length=10)
    @Getter @Setter private String date;

    @Getter @Setter private boolean moderated;
    @Getter @Setter private String moderator;
	
	public Comment(Blogger author, BlogPost blog, String content) throws IllegalArgumentException {
		this.author = author;
		this.blog = blog;
		this.setContent(content);
		date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
	}

	public void setContent(String content) throws IllegalArgumentException{
		if(content.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a comment!");
		}
		this.content = content;
	}

	public void moderated() {
		moderated = true;
	}
}
