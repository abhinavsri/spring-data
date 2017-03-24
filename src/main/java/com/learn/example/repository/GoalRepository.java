package com.learn.example.repository;

import com.learn.example.model.User;
import com.learn.example.model.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goalRepository")
public interface GoalRepository extends BaseRepository<Goal, Long> {

    List<Goal> findByName(String name);

    List<Goal> findByUser(User user);

    Page<Goal> findAll(Pageable pageable);
}
