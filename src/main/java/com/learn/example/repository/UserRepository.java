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

    List<User> findUsersByUsernameAndPassword(String u, String p);

    User findFirstByUsernameAndPassword(String u, String p);

    List<User> findUsersByIdIn(List<Long> userIdList);

    User findByFirstName(String firstName);

    User findByFirstname(String firstName);

    @Query("SELECT user FROM User user WHERE LOWER(user.firstName) = LOWER(:firstName)")
    User find(@Param("firstName") String firstName);


    List<User> findByGoalSetIn(List<Goal> goals);

}