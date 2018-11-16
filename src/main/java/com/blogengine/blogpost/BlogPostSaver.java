package com.blogengine.blogpost;

public interface BlogPostSaver {
	BlogPost findById(Long id);
	BlogPost save(BlogPost bl);
}
