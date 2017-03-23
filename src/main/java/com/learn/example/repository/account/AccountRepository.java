package com.learn.example.repository.account;

import com.learn.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account,Long>,AccountRepositoryCustom {
}
