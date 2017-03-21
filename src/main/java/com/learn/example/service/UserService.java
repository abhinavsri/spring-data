package com.learn.example.service;

import com.learn.example.model.User;
import com.learn.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserRepository userDao;
	
	public boolean login( String  username, String password ) {
		List<User > u = userDao.findUsersByUsernameAndPassword(username, password);
		
		if ( u.size() == 1 ) return true;
		else return false;
		
	}
}