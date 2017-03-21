package com.learn.example.repository;

import com.learn.example.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findUsersByUsernameAndPassword(String u, String p);
	User findUserByUsernameAndPassword(String u, String p);


/*
    List<User> findAll();
      User save(User user);*/

}