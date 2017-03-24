package com.learn.example.repository;

import com.learn.example.model.Goal;
import com.learn.example.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    User findByUsername(String username);

//    List<User> findFirstByPassword(String p);

    List<User> findUsersByFirstNameIn(List<String> userIdList);

    User findByFirstName(String firstName);

    User findByFirstname(String firstName);

    @Query("SELECT user FROM User user WHERE LOWER(user.firstName) = LOWER(:firstName)")
    User find(@Param("firstName") String firstName);


    List<User> findByGoalSetIn(List<Goal> goals);

}