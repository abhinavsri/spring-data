package com.learn.example;


import com.learn.example.config.BasicConfig;
import com.learn.example.model.Goal;
import com.learn.example.model.User;
import com.learn.example.repository.GoalRepository;
import com.learn.example.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicConfig.class})
public class GoalRepositoryIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalRepository goalRepository;

    @Test
    public void save() {
        List<User> userList = new ArrayList<User>();
        userList.add(userRepository.findFirstByUsernameAndPassword("Ali", "Tanwir"));
        userList.add(userRepository.findFirstByUsernameAndPassword("Akash", "Katiyar"));
        userList.add(userRepository.findFirstByUsernameAndPassword("Nakul", "Ashish"));

        for (User user : userList) {
            goalRepository.save(new Goal("Sedan",user));
            goalRepository.save(new Goal("SUV",user));
            goalRepository.save(new Goal( "Sports",user));
        }


    }

    @Test
    public void find() {
        User ali=userRepository.findFirstByUsernameAndPassword("Ali", "Tanwir");
        List<Goal> goals = goalRepository.findByUser(ali);

        assertThat(ali, is(notNullValue()));
        assertThat(ali.getId(), is(notNullValue()));
        assertThat(ali.getUsername(), is("Ali"));
        assertThat(ali.getPassword(), is("Tanwir"));



        assertThat(goals.size(), is(2));
        assertThat(goals.get(0).getName(), is("Sedan"));
        assertThat(goals.get(1).getName(), is("SUV"));



    }


}
