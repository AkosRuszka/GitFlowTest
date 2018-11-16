package com.blogengine.blogger;

public interface BloggerSaver {
	Blogger findById(Long id);
	Blogger findByEmail(String email);
	Blogger findByUserName(String username);
	
	Blogger save(Blogger bl);
}
