package com.blogengine.blogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.blogengine.role.Role;
import com.blogengine.blogpost.BlogPost;
import com.blogengine.comment.Comment;
import com.blogengine.validator.EmailValidator;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Blogger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long ID;
	
	@Column(length=50)
	@Getter @Setter private String lastName;
	
	@Column(length=50)
	@Getter @Setter private String firstName;
	
	@Getter private short age;
	
	@Column(length=50, nullable=false, updatable=false)
	@Getter private String userName;
	
	@Column(length=50, nullable=false, unique=true, updatable=false)
	@Getter private String emailAddress;
	
	@Column(nullable=false)
	@Getter private String password;
	
	@Getter @Setter private short blogPostCounter;
	
	@Getter @Setter private String regDate;
	@Getter @Setter private String lastActivityDate;
	
	@JsonBackReference(value="blogposts")
	@OneToMany(mappedBy = "author")
	@Getter @Setter private List<BlogPost> blogposts;
	
	@JsonBackReference(value="comments")
	@OneToMany(mappedBy = "author")
	@Getter @Setter private List<Comment> comments; 
	
	@JsonBackReference(value="roles")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
	)
	@Getter @Setter private Set<Role> roles;
	
	@ElementCollection
	@CollectionTable(name="Follows", joinColumns= @JoinColumn(name="ID"))
	@Getter @Setter private List<Long> followedBlogPostsID;
	
	public Blogger(@NonNull String lastName,@NonNull String firstName, short age,
				   @NonNull String userName,@NonNull String emailAddress,@NonNull String password) throws IllegalArgumentException {
		this.lastName = lastName;
		this.firstName = firstName;
		this.setAge(age);
		this.setUserName(userName);
		this.setEmailAddress(emailAddress);
		this.password = password;
		
		this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		this.lastActivityDate = regDate;
		this.blogPostCounter = 0;
		this.blogposts = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.followedBlogPostsID = new ArrayList<>();
	}
	
	public void setAge(short age) throws IllegalArgumentException {
		if(age < 10 || age > 100) {
			throw new IllegalArgumentException("A megadott kor nem megfelelő tartományban (10<kor<100) van!");
		}
		this.age = age;
	}
	
	private void setUserName(@NonNull String userName) throws IllegalArgumentException {
		if(userName.isEmpty()) {
			throw new IllegalArgumentException("Username nem lehet üres!");
		}
		this.userName = userName;
	}

	private void setEmailAddress(@NonNull String emailAddress) throws IllegalArgumentException {
		if(emailAddress.isEmpty()) {
			throw new IllegalArgumentException("Emailcím nem lehet üres!");
		}

		if(EmailValidator.getInstance().emailValidate(emailAddress)) {
			this.emailAddress = emailAddress;	
		} else {
			throw new IllegalArgumentException("Nem megfelelő email formátum (teszt@gmail.com)");
		}	
	}

	public void setPassword(@NonNull String pass) {
		if(pass.length() >= 5) this.password = pass;
		else throw new IllegalArgumentException("Túl rövid jelszó (min 5 karakter)");
	}

	public void addComment(@NonNull Comment comment) {
		comments.add(comment);
	}
	
	public void addBlogPost(@NonNull BlogPost newPost){
		blogposts.add(newPost);
		blogPostCounter++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + emailAddress.hashCode();
		result = prime * result + userName.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blogger other = (Blogger) obj;

		if(emailAddress.equals(other.emailAddress)) {
			return true;
		}
		return userName.equals(other.userName);
	}

	public void addFollowedBlogPost(@NonNull Long blogpost_id) {
		this.followedBlogPostsID.add(blogpost_id);		
	}	
	
}
