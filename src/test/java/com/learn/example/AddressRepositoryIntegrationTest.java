package com.learn.example;

import com.learn.example.config.BasicConfig;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.learn.example.model.Address;
import com.learn.example.model.User;
import com.learn.example.repository.AddressRepository;
import com.learn.example.repository.UserRepository;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicConfig.class})
public class AddressRepositoryIntegrationTest {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void pagination() {
        int offset = 1;
        int max = 2;

        Page<Address> addressPage = addressRepository.findAll(new PageRequest(offset, max));

        List<Address> addressList = addressPage.getContent();

        //For three records
        assertThat(addressPage.getTotalElements(), is(3L));
        assertThat(addressPage.getTotalPages(), is(2));
        assertThat(addressList.size(), is(1));

    }

    @Test
    public void sorting() {
        int offset = 0;
        int max = 2;

        Page<Address> addressPage = addressRepository.findAll(new PageRequest(offset, max, new Sort(Sort.Direction.ASC, "street")));

        List<Address> addressList = addressPage.getContent();

        assertThat(addressPage.getTotalElements(), is(3L));
        assertThat(addressPage.getTotalPages(), is(2));
        assertThat(addressList.size(), is(2));
        assertThat(addressList.get(0).getStreet(), is("Andheri"));
    }

    @Test
    public void association() {
        User user = userRepository.findByUsername("akash@fintechlabs.in");
        Address address = addressRepository.findByUser(user);

        assertThat(address.getCity(), is("Delhi"));
        assertThat(address.getCountry(), is("India"));
        assertThat(address.getStreet(), is("Cannought Palace"));
        assertThat(address.getUser(), is(user));
    }
}
