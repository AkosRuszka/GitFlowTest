package com.blogengine.blogger;

public interface BloggerSaver {
	Blogger findById(Long id);
	Blogger findByEmail(String email);	
	Blogger save(Blogger blogger);
}
