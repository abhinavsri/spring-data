package com.learn.example;

import com.learn.example.config.BasicConfig;
import com.learn.example.model.Account;
import com.learn.example.repository.account.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicConfig.class})
public class AccountCustomImplementationIntegrationTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void save(){

        accountRepository.save(new Account("Swiss",12356));
        accountRepository.save(new Account("SwissRbs",12356));
        List<Account> accounts=accountRepository.getAccountsByName("Swiss");


        assertThat(accounts, is(notNullValue()));
        assertThat(accounts.size(), is(2));
        assertThat(accounts.get(0).getName(), is("Swiss"));
        assertThat(accounts.get(1).getName(), is("SwissRbs"));
    }

}
