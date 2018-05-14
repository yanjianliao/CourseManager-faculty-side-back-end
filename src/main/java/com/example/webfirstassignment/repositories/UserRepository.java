package com.example.webfirstassignment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webfirstassignment.models.User;


public interface UserRepository extends CrudRepository<User, Integer>{
	
	
}