package com.blogengine.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blogengine.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
