package com.blogengine.role;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.blogengine.blogger.Blogger;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Blogger> bloggers;

	public Role(String name) {
		role = name;
	}

	@Override
	public int hashCode() {
		return role.hashCode();
	}
	
}
