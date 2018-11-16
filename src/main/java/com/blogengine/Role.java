package com.blogengine;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.blogengine.blogger.Blogger;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Blogger> bloggers = new HashSet();
	
	public Role() { /* empty */ }

	public Role(String name) {
		role = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Blogger> getBloggers() {
		return bloggers;
	}

	public void setBloggers(Set<Blogger> bloggers) {
		this.bloggers = bloggers;
	}
	
	@Override
	public int hashCode() {
		return role.hashCode();
	}
	
}
