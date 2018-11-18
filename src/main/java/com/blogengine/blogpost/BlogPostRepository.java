package com.blogengine.blogpost;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
	
	public List<BlogPost> findAll();

	public Optional<BlogPost> findFirst1ByTitle(String title);

	@Query(value="Select * from blog_post where title like CONCAT('%',:title,'%')",nativeQuery=true)
	public List<BlogPost> findByTitle(@Param("title") String title);
	
	public List<BlogPost> findByPostedDateOrderByPostedDateAsc(String posteddate);
	
	public List<BlogPost> findByPostedDateOrderByPostedDateDesc(String posteddate);

	public List<BlogPost> findByPostedDate(String posteddate);
		
	public Optional<BlogPost> findById(Long id);
}
