package com.learn.example;

import com.learn.example.config.BasicConfig;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import com.learn.example.model.User;
import com.learn.example.repository.UserRepository;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicConfig.class})
public class UserRepositoryIntegrationtest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void savesNewUser() {

        User user = new User("Stefan", "Lassard");
        List<User> userList;
        User akash = new User("Akash", "Katiyar");
        User ali = new User("Nakul", "Ashish");
        User nakul = new User("Ali", "Tanwir");
        User result = userRepository.save(user);
        userRepository.save(akash);
        userRepository.save(ali);
        userRepository.save(nakul);

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(notNullValue()));
        assertThat(result.getUsername(), is("Stefan"));
        assertThat(result.getPassword(), is("Lassard"));
    }

    @Test
    public void updateuser() {
        User user = userRepository.findOne(2L);
        user.setPassword("Butler");
        user = userRepository.save(user);


        assertThat(user, is(notNullValue()));
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getUsername(), is("Stefan"));
        assertThat(user.getPassword(), is("Butler"));
    }


    @Test
    public void deleteUser() {
        userRepository.delete(2L);
    }

    @Test
    public void deleteUsers(){
        Iterable<User> userIterable=userRepository.findAll();
        userRepository.delete(userIterable);
    }

    @Test
    public void findAll() {
        List<User> userList = userRepository.findUsersByUsernameAndPassword("Ali","Tanwir");
//        List<User> userList = userRepository.findAll();
//        assertThat(userList, is(notNullValue()));
        assertThat(userList.size(), is(1));

    }

    @Test
    public void findUser() {

        User result = userRepository.findUserByUsernameAndPassword("Stefan", "Lassard");

        assertThat(result, is(notNullValue()));
        assertThat(result.getUsername(), is("Stefan"));
        assertThat(result.getPassword(), is("Lassard"));
    }

}
