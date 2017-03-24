package com.learn.example.service;

import com.learn.example.model.User;
import com.learn.example.repository.UserRepository;
import com.learn.example.util.SearchDTO;
import com.learn.example.util.SearchType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nexthoughts on 24/3/17.
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public User search(SearchDTO searchCriteria) {
        LOGGER.debug("Searching persons with search criteria: " + searchCriteria);

        String searchTerm = searchCriteria.getSearchTerm();
        SearchType searchType = searchCriteria.getSearchType();

        if (searchType == null) {
            throw new IllegalArgumentException();
        }

        return findPersonsBySearchType(searchTerm, searchType);
    }

    private User findPersonsBySearchType(String searchTerm, SearchType searchType) {
        User user;

        if (searchType == SearchType.METHOD_NAME) {
            LOGGER.debug("Searching persons by using method name query creation.");
            user = userRepository.findByFirstName(searchTerm);
        } else if (searchType == SearchType.NAMED_QUERY) {
            LOGGER.debug("Searching persons by using named query");
            user = userRepository.findByFirstname(searchTerm);
        } else {
            LOGGER.debug("Searching persons by using query annotation");
            user = userRepository.find(searchTerm);
        }

        return user;
    }
}
