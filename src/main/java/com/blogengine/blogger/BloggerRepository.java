package com.blogengine.blogger;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends CrudRepository<Blogger, Long> {

	List<Blogger> findAll();
	
	Optional<Blogger> findFirst1ByEmailAddress(String email);
	
	Optional<Blogger> findFirst1ByUserName(String username);
	
	Optional<Blogger> findByEmailAddress(String email);
}
