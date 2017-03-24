package com.learn.example.service;

import com.learn.example.model.Address;
import com.learn.example.model.Goal;
import com.learn.example.model.User;
import com.learn.example.repository.AddressRepository;
import com.learn.example.repository.GoalRepository;
import com.learn.example.repository.UserRepository;
import com.learn.example.util.Constants;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BootStrap implements InitializingBean {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UserService userService;

    @Autowired
    AddressRepository addressRepository;

    private final Logger log = org.slf4j.LoggerFactory.getLogger(BootStrap.class);

    @Override
    @Transactional
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("=====================ajskdkajshdk");
        if (userRepository.findAll().isEmpty()) {
            System.out.println("=====================ajskdkajshdk");
            createUser();
        }


    }

    public void createUser() {
        List<User> userList = new ArrayList<User>();
        User ali = new User("ali@fintechlabs.in", "123456", "Ali", "Katiyar");
        User user = new User("stefan@fintechlabs.in", "123456", "Stefan", "Lassard");
        User akash = new User("Akash@fintechlabs.in", "123456", "Akash", "Katiyar");
        User nakul = new User("nakul@fintechlabs.in", "123456", "Ashish", "Nakul");
        userList.add(userRepository.save(ali));
        userList.add(userRepository.save(akash));
        userList.add(userRepository.save(user));
        userList.add(userRepository.save(nakul));

        createGoals(userList);

        createAddress(userList);
    }

    public void createGoals(List<User> userList) {

        for (User user : userList) {
            goalRepository.save(new Goal(Constants.WEEKLY_GOAL, user));
            goalRepository.save(new Goal(Constants.MONTHLY_GOAL, user));
            goalRepository.save(new Goal(Constants.QUARTERLY_GOAL, user));
        }
    }

    public void createAddress(List<User> users) {
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(new Address("Cannought Palace", "Mumbai", "India", users.get(0)));
        addressList.add(new Address("Dalal Street", "Delhi", "India", users.get(1)));
        addressList.add(new Address("Andheri", "Mumbai", "India", users.get(2)));
        addressList.add(new Address("India Gate", "Delhi", "India", users.get(3)));
        addressRepository.save(addressList);

    }
}
