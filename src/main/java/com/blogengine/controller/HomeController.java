package com.blogengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogengine.domain.Blogger;
import com.blogengine.service.BloggerService;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String homeIndex() {
		return "Kezdő Controller";
	}	
	
}
