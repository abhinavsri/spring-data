package com.learn.example.repository.account;

import com.learn.example.model.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository

public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Account> getAccountsByName(String name) {
        Query query = entityManager.createQuery("SELECT acc FROM Account as acc WHERE acc.name LIKE:searchName" /*+
                "WHERE acc.name LIKE ?"*/, Account.class);
        query.setParameter("searchName", "%" + name + "%");
        return query.getResultList();
    }
}
