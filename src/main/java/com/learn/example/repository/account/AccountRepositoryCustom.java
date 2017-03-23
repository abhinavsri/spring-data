package com.learn.example.repository.account;


import com.learn.example.model.Account;

import java.util.List;

public interface AccountRepositoryCustom {
    List<Account> getAccountsByName(String firstName);


}
