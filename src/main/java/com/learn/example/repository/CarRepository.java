package com.learn.example.repository;

import com.learn.example.model.Car;
import com.learn.example.model.User;
//import com.learn.example.model.User;

import java.util.List;

public interface CarRepository extends BaseRepository<Car, Long> {

    List<Car> findByModel(String model);
    List<Car> findByUser(User user);
    List<Car> findByUserUsername(String username);
}
