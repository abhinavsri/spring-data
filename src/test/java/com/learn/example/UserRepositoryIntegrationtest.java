package com.learn.example;

import com.learn.example.config.BasicConfig;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

import com.learn.example.model.Goal;
import com.learn.example.model.User;
import com.learn.example.repository.GoalRepository;
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
    GoalRepository goalRepository;

    @Autowired
    UserService userService;


    private static final String USERNAME = "stefan@fintechlabs.in";
    private static final String PASSWORD = "123456";
    private static final String FIRST_NAME = "Stefan";
    private static final String LAST_NAME = "Lassard";

    @Test
    public void save() {
        User user = new User(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME);
        User result = userRepository.save(user);

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(notNullValue()));
        assertThat(result.getUsername(), is("Stefan"));
        assertThat(result.getPassword(), is("Lassard"));
    }

    @Test
    public void updateuser() {
        User user = userRepository.findByUsername(USERNAME);
        user.setPassword("Butler");
        user = userRepository.save(user);

        assertThat(user, is(notNullValue()));
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getUsername(), is("Stefan"));
        assertThat(user.getPassword(), is("Butler"));
    }


    @Test
    public void delete() {
        User user = userRepository.findByUsername(USERNAME);
        userRepository.delete(user.getId());

        assertThat(userRepository.findByUsername(USERNAME), is(nullValue()));
    }

    @Test
    public void deleteAll() {
        Iterable<User> userIterable = userRepository.findAll();
        userRepository.delete(userIterable);

        assertThat(userRepository.findAll().isEmpty(), is(true));
    }

    /*@Test
    public void findFirstUserByPassword() {
        List<User> results = userRepository.findFirstByPassword("123456");
        User user = results.get(0);

        assertThat(results, is(notNullValue()));
        assertThat(results.size(), is(1));
        assertThat(user.getUsername(), is("Ali"));
        assertThat(user.getPassword(), is("Tanwir"));
    }
*/
    @Test
    public void findInList() {
        List<String> userIdList = new ArrayList<String>();
        userIdList.add("Ali");
        userIdList.add("Akash");
        List<User> userList = userRepository.findUsersByFirstNameIn(userIdList);

        assertThat(userList, is(notNullValue()));
        assertThat(userList.size(), is(2));
    }

    @Test
    public void searchWhenSearchTypeIsMethodName() {
        SearchDTO searchCriteria = createSearchDTO(FIRST_NAME, SearchType.METHOD_NAME);

        User user = userService.search(searchCriteria);

        assertEquals(user.getFirstName(), FIRST_NAME);
    }

    @Test
    public void searchWhenSearchTypeIsNamedQuery() {
        SearchDTO searchCriteria = createSearchDTO(FIRST_NAME, SearchType.NAMED_QUERY);
        User user = userService.search(searchCriteria);

        assertEquals(user.getFirstName(), FIRST_NAME);
    }

    @Test
    public void searchWhenSearchTypeIsQueryAnnotation() {
        SearchDTO searchCriteria = createSearchDTO(FIRST_NAME, SearchType.QUERY_ANNOTATION);
        User user = userService.search(searchCriteria);

        assertEquals(user.getFirstName(), FIRST_NAME);
    }

    private SearchDTO createSearchDTO(String searchTerm, SearchType searchType) {
        SearchDTO searchCriteria = new SearchDTO();
        searchCriteria.setSearchTerm(searchTerm);
        searchCriteria.setSearchType(searchType);
        return searchCriteria;
    }


}
