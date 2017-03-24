package com.learn.example;

import com.learn.example.config.BasicConfig;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import com.learn.example.model.Car;
import com.learn.example.model.User;
import com.learn.example.repository.CarRepository;
import com.learn.example.repository.UserRepository;
import com.learn.example.service.UserService;
import com.learn.example.util.SearchDTO;
import com.learn.example.util.SearchType;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicConfig.class})
public class UserRepositoryIntegrationtest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserService userService;


    private static final String FIRST_NAME = "Stefan";

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
    public void deleteUsers() {
        Iterable<User> userIterable = userRepository.findAll();
        userRepository.delete(userIterable);
    }

    @Test
    public void findAll() {
        List<User> userList = userRepository.findUsersByUsernameAndPassword("Ali", "Tanwir");
//        List<User> userList = userRepository.findAll();
//        assertThat(userList, is(notNullValue()));
        assertThat(userList.size(), is(3));

    }

    @Test
    public void findUser() {

        User result = userRepository.findFirstByUsernameAndPassword("Stefan", "Lassard");

        assertThat(result, is(notNullValue()));
        assertThat(result.getUsername(), is("Stefan"));
        assertThat(result.getPassword(), is("Lassard"));
    }

    @Test
    public void findInList() {
        List<Long> userIdList = new ArrayList<Long>();
        userIdList.add(1L);
        userIdList.add(2L);
        List<User> userList = userRepository.findUsersByIdIn(userIdList);
        System.out.println("---------------------------------------" + userList);
        System.out.println("---------------------------------------" + userList.size());
    }

    @Test
    public void findInCars() {

        Page<Car> pages = carRepository.findAll(new PageRequest(0, 2));
        List<Car> cars = pages.getContent();
        List<User> userList = userRepository.findByCarSetIn(cars);
        System.out.println("---------------------------------------" + cars);
        System.out.println("---------------------------------------" + cars.size());
        System.out.println("---------------------------------------" + userList);
        System.out.println("---------------------------------------" + userList.size());
    }


    @Test
    public void searchWhenSearchTypeIsMethodName() {
        SearchDTO searchCriteria = createSearchDTO(FIRST_NAME, SearchType.METHOD_NAME);

        User user = userService.search(searchCriteria);

        assertEquals(user.getLastName(), FIRST_NAME);
    }

    @Test
    public void searchWhenSearchTypeIsNamedQuery() {
        SearchDTO searchCriteria = createSearchDTO(FIRST_NAME, SearchType.NAMED_QUERY);
        User user = userService.search(searchCriteria);

        assertEquals(user.getLastName(), FIRST_NAME);
    }

    @Test
    public void searchWhenSearchTypeIsQueryAnnotation() {
        SearchDTO searchCriteria = createSearchDTO(FIRST_NAME, SearchType.QUERY_ANNOTATION);
        User user = userService.search(searchCriteria);

        assertEquals(user.getLastName(), FIRST_NAME);
    }

    private SearchDTO createSearchDTO(String searchTerm, SearchType searchType) {
        SearchDTO searchCriteria = new SearchDTO();
        searchCriteria.setSearchTerm(searchTerm);
        searchCriteria.setSearchType(searchType);
        return searchCriteria;
    }


}
