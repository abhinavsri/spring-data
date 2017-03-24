package com.learn.example.repository;


import com.learn.example.model.Address;
import com.learn.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUser(User user);
}
