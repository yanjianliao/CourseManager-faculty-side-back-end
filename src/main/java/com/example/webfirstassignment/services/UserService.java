package com.example.webfirstassignment.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webfirstassignment.models.User;
import com.example.webfirstassignment.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@GetMapping("/api/user")
	public Iterable<User> findAllHellos() {
		return repository.findAll();
	}
}
