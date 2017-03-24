package com.learn.example.repository;

import com.learn.example.model.Car;
import com.learn.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import com.learn.example.model.User;

import java.util.List;


public interface CarRepository extends BaseRepository<Car, Long> {

    List<Car> findByModel(String model);

    List<Car> findByUser(User user);

    Page<Car> findAll(Pageable pageable);
}
