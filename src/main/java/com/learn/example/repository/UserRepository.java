package com.learn.example.repository;

import com.learn.example.model.Car;
import com.learn.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUsersByUsernameAndPassword(String u, String p);

    User findFirstByUsernameAndPassword(String u, String p);

    List<User> findUsersByIdIn(List<Long> userIdList);
    List<User> findByCarSetIn(List<Car> cars);

    User findByFirstName(String firstName);

    User findByFirstname(String firstName);

    @Query("SELECT user FROM User user WHERE LOWER(user.firstName) = LOWER(:firstName)")
    User find(@Param("firstName") String firstName);
}