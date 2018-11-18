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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class BlogPost {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
    @Getter @Setter private Long ID;
	
	@ManyToOne
    @Getter @Setter private Blogger author;
	
	@Column(length=50)
    @Getter	private String title;
	
	@Column(columnDefinition="TEXT")
    @Getter private String content;
	
	@Column(length=10)
    @Getter @Setter private String postedDate;
	
	@OneToMany(mappedBy="blog")
    @Getter @Setter private List<Comment> comments;
	
	@ElementCollection
	@CollectionTable(name="Followers", joinColumns= @JoinColumn(name="ID"))
    @Getter @Setter private List<Long> followersBloggerID;

	public BlogPost(@NonNull Blogger author,@NonNull String title,@NonNull String content) throws IllegalArgumentException {
		this.author = author;
		this.setTitle(title);
		this.setContent(content);
		this.postedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
		comments = new ArrayList<>();
		followersBloggerID = new ArrayList<>();
	}

	public void setTitle(@NonNull String title) throws IllegalArgumentException{
		if(title.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a cím!");
		}
		this.title = title;
	}

	public void setContent(@NonNull String content) throws IllegalArgumentException{
		if(content.isEmpty()) {
			throw new IllegalArgumentException("Nem lehet üres a tartalom!");
		}
		this.content = content;
	}
	
	public void addComment(@NonNull Comment comment) {
		comments.add(comment);
	}

	public void addFollower(@NonNull Long bloggerID) {
		this.followersBloggerID.add(bloggerID);
	}
}
