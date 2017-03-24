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
        User ali = new User("Ali@fintechlabs.in", "123456", "Ali", "Katiyar");
        User user = new User("Stefan@fintechlabs.in", "123456", "Stefan", "Lassard");
        User akash = new User("Akash@fintechlabs.in", "123456", "Akash", "Katiyar");
        User nakul = new User("Nakul@fintechlabs.in", "123456", "Ashish", "Nakul");
        userList.add(userRepository.save(ali));
        userList.add(userRepository.save(akash));
        userList.add(userRepository.save(user));
        userList.add(userRepository.save(nakul));

        createGoals(userList);


        createAddress(ali);
        createAddress(user);
        createAddress(akash);
        createAddress(nakul);
    }

    public void createGoals(List<User> userList) {

        for (User user : userList) {
            goalRepository.save(new Goal(Constants.WEEKLY_GOAL, user));
            goalRepository.save(new Goal(Constants.MONTHLY_GOAL, user));
            goalRepository.save(new Goal(Constants.QUATERLEY_GOAL, user));

        }
    }

    public void createAddress(User user) {
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(new Address("Dalal Street", "Mumbai", "India",user));
        addressList.add(new Address("Cannought Palace", "Delhi", "India",user));
        addressList.add(new Address("Andheri", "Mumbai", "India",user));
        addressList.add(new Address("India Gate", "Delhi", "India",user));
        addressList.add(new Address("Juhu", "Mumbai", "India",user));
        addressRepository.save(addressList);

    }
}
