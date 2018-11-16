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

import com.blogengine.Role;
import com.blogengine.blogpost.BlogPost;
import com.blogengine.comment.Comment;
import com.blogengine.validator.EmailValidator;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Blogger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(length=50)
	private String lastName;
	
	@Column(length=50)
	private String firstName;
	
	private short age;
	
	@Column(length=50, nullable=false)
	private String userName;
	
	@Column(length=50, nullable=false, unique=true)
	private String emailAddress;
	
	@Column(nullable=false)
	private String password;
	
	private short blogPostCounter;
	
	private String regDate;
	private String lastActivityDate;
	
	@JsonBackReference(value="blogposts")
	@OneToMany(mappedBy = "author")
	private List<BlogPost> blogposts;
	
	@JsonBackReference(value="comments")
	@OneToMany(mappedBy = "author")
	private List<Comment> comments; 
	
	@JsonBackReference(value="roles")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
	)
	private Set<Role> roles;
	
	@ElementCollection
	@CollectionTable(name="Follows", joinColumns= @JoinColumn(name="ID"))
	private List<Long> followedBlogPostsID;

	public Blogger() { /* empty for hibernate */ }
	
	public Blogger(String lastName, String firstName, short age, String userName, String emailAddress) throws IllegalArgumentException {
		super();
		
		//Validálás setterekkel
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setAge(age);
		this.setUserName(userName);
		this.setEmailAddress(emailAddress);
		
		this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.lastActivityDate = regDate;
		this.blogPostCounter = 0;
		this.blogposts = new ArrayList<BlogPost>();
		this.comments = new ArrayList<Comment>();
	}

	public String getLastName() {
		return lastName;
	}	
	public void setLastName(String lastName) throws IllegalArgumentException{
		if(lastName.isEmpty()) {
			throw new IllegalArgumentException("Vezetéknév nem lehet üres!");	
		}
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws IllegalArgumentException{
		if(firstName.isEmpty()) {
			throw new IllegalArgumentException("Keresztnév nem lehet üres!");
		}
		this.firstName = firstName;
	}
	
	public short getAge() {
		return age;
	}
	public void setAge(short age) throws IllegalArgumentException{
		//Kor validálás
		if(age < 10 || age > 100) {
			throw new IllegalArgumentException("Tiltott kor!");
		}
		this.age = age;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) throws IllegalArgumentException{
		if(userName.isEmpty()) {
			throw new IllegalArgumentException("Username nem lehet üres!");
		}
		this.userName = userName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) throws IllegalArgumentException{
		if(emailAddress.isEmpty()) {
			throw new IllegalArgumentException("Emailcím nem lehet üres!");
		}
		
		//Email validálás	
		if(EmailValidator.getInstance().emailValidate(emailAddress)) {
			this.emailAddress = emailAddress;	
		} else {
			throw new IllegalArgumentException("Rossz email formátum!");
		}	
	}
	
	public short getBlogPostCounter() {
		return blogPostCounter;
	}
	public void setBlogPostCounter(short blogPostCounter) {
		this.blogPostCounter = blogPostCounter;
	}
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getLastActivityDate() {
		return lastActivityDate;
	}
	public void setLastActivityDate(String lastActivity) {
		this.lastActivityDate = lastActivity;
	}

	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}

	public List<BlogPost> getBlogposts() {
		return blogposts;
	}

	public void setBlogposts(List<BlogPost> blogposts) {
		this.blogposts = blogposts;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public void addBlogPost(String title, String content) throws IllegalArgumentException {
		blogposts.add(new BlogPost(this,title,content));
		blogPostCounter++;
	}
	
	public List<Long> getFollowedBlogPostsID() {
		return followedBlogPostsID;
	}

	public void setFollowedBlogPostsID(List<Long> followedBlogPostsID) {
		this.followedBlogPostsID = followedBlogPostsID;
	}

	@Override
	public String toString() {
//		return "Blogger [lastName=" + lastName + ", firstName=" + firstName + ", age=" + age + ", userName=" + userName
//				+ ", emailAddress=" + emailAddress + ", blogPostCounter=" + blogPostCounter + ", regDate=" + regDate
//				+ ", lastActivity=" + lastActivityDate + "]";
		return String.format(""
				+ "%1$s blogger adatai\n"
				+ "név: %2$s %3$s%n"
				+ "kor: %4$s%n"
				+ "email cím: %5$s%n"
				+ "posztok száma: %6$d%n"
				+ "regisztráció dátuma: %7$s%n"
				+ "utoljára aktív: %8$s%n", userName,lastName,firstName,age,emailAddress,blogPostCounter,regDate,lastActivityDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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

		if(emailAddress.equals(other.getEmailAddress())) {
			return true;
		}
		if(userName.equals(other.getUserName())) {
			return true;
		}
		return false;
	}

	public void addFollowedBlogPost(Long blogpost_id) {
		this.followedBlogPostsID.add(blogpost_id);		
	}	
	
}
