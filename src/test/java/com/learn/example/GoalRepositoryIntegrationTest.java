package com.learn.example;


import com.learn.example.config.BasicConfig;
import com.learn.example.model.Goal;
import com.learn.example.model.User;
import com.learn.example.repository.GoalRepository;
import com.learn.example.repository.UserRepository;
import com.learn.example.util.Constants;
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
    public void findOne() {
        User stephen = userRepository.findOne(3L);
        List<Goal> goals = goalRepository.findByUser(stephen);

        assertThat(stephen, is(notNullValue()));
        assertThat(stephen.getId(), is(notNullValue()));
        assertThat(stephen.getUsername(), is("Stefan@fintechlabs.in"));
        assertThat(stephen.getPassword(), is("123456"));

        assertThat(goals.size(), is(3));
        assertThat(goals.get(0).getName(), is(Constants.WEEKLY_GOAL));
        assertThat(goals.get(1).getName(), is(Constants.MONTHLY_GOAL));
        assertThat(goals.get(2).getName(), is(Constants.QUARTERLY_GOAL));
    }

    @Test
    public void findByUser() {
        User ali = userRepository.findByUsername("Stefan@fintechlabs.in");
        List<Goal> goals = goalRepository.findByUser(ali);

        assertThat(ali, is(notNullValue()));
        assertThat(ali.getId(), is(notNullValue()));
        assertThat(ali.getUsername(), is("Stefan@fintechlabs.in"));
        assertThat(ali.getPassword(), is("123456"));

        assertThat(goals.size(), is(3));
        assertThat(goals.get(0).getName(), is(Constants.WEEKLY_GOAL));
        assertThat(goals.get(1).getName(), is(Constants.MONTHLY_GOAL));
        assertThat(goals.get(3).getName(), is(Constants.QUARTERLY_GOAL));
    }

}
