package com.blogengine.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.blogengine.validator.EmailValidator;

@DynamicUpdate
public class Blogger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	private String lastName;
	private String firstName;
	private short age;
	
	private String userName;
	
	private String emailAddress;
	
	private short blogPostCounter;
	
	private Date regDate;
	private Date lastActivity;
	
	@OneToMany(mappedBy = "blogger")
	private List<BlogPost> blogposts;
	
	@OneToMany(mappedBy = "blogger")
	private List<Comment> comments; 
	
	@SuppressWarnings("unused")
	public Blogger() {
		// for hibernate
		super();
	}
	
	public Blogger(String lastName, String firstName, short age, String userName, String emailAddress, Date regDate) throws Exception {
		super();
		
		//Validálás setterekkel
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setAge(age);
		this.setUserName(userName);
		this.setEmailAddress(emailAddress);
		
		this.regDate = regDate;
		this.blogPostCounter = 0;
		this.blogposts = new ArrayList<BlogPost>();
		this.comments = new ArrayList<Comment>();
	}

	public String getLastName() {
		return lastName;
	}	
	public void setLastName(String lastName) throws Exception{
		if(lastName.isEmpty()) {
			throw new IllegalArgumentException("Vezetéknév nem lehet üres!");	
		}
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws Exception{
		if(firstName.isEmpty()) {
			throw new IllegalArgumentException("Keresztnév nem lehet üres!");
		}
		this.firstName = firstName;
	}
	
	public short getAge() {
		return age;
	}
	public void setAge(short age) throws Exception{
		//Kor validálás
		if(age < 10 || age > 100) {
			throw new IllegalArgumentException("Tiltott kor!");
		}
		this.age = age;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		if(userName.isEmpty()) {
			throw new IllegalArgumentException("Username nem lehet üres!");
		}
		this.userName = userName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
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
		if(blogPostCounter < 0) {
			throw new IllegalArgumentException("Hibás blogpostCounter!");
		}
		this.blogPostCounter = blogPostCounter;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public Date getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public List<BlogPost> getBlogpost() {
		return blogposts;
	}
	public void setBlogpost(List<BlogPost> blogpost) {
		this.blogposts = blogpost;
	}

	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
