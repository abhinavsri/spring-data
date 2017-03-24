package com.learn.example.repository;

import com.learn.example.model.Car;
import com.learn.example.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findUsersByUsernameAndPassword(String u, String p);
	User findFirstByUsernameAndPassword(String u, String p);

    List<User> findUsersByIdIn(List<Long> userIdList);
    List<User> findByCarSetIn(List<Car> cars);

}