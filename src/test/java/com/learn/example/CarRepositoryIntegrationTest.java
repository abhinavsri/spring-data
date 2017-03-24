package com.learn.example;


import com.learn.example.config.BasicConfig;
import com.learn.example.model.Car;
import com.learn.example.model.User;
import com.learn.example.repository.CarRepository;
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
public class CarRepositoryIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    @Test
    public void save() {
        List<User> userList = new ArrayList<User>();
        userList.add(userRepository.findFirstByUsernameAndPassword("Ali", "Tanwir"));
        userList.add(userRepository.findFirstByUsernameAndPassword("Akash", "Katiyar"));
        userList.add(userRepository.findFirstByUsernameAndPassword("Nakul", "Ashish"));

        for (User user : userList) {
            carRepository.save(new Car(user, "Sedan"));
            carRepository.save(new Car(user, "SUV"));
            carRepository.save(new Car(user, "Sports"));
        }


    }

    @Test
    public void find() {
        User ali=userRepository.findFirstByUsernameAndPassword("Ali", "Tanwir");
        List<Car> cars = carRepository.findByUser(ali);

        assertThat(ali, is(notNullValue()));
        assertThat(ali.getId(), is(notNullValue()));
        assertThat(ali.getUsername(), is("Ali"));
        assertThat(ali.getPassword(), is("Tanwir"));



        assertThat(cars.size(), is(2));
        assertThat(cars.get(0).getModel(), is("Sedan"));
        assertThat(cars.get(1).getModel(), is("SUV"));



    }


}
