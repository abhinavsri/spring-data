package com.learn.example;

import com.learn.example.config.BasicConfig;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.learn.example.model.Address;
import com.learn.example.repository.AddressRepository;
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

    @Test
    public void saveAddress() {
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(new Address("Dalal Street", "Mumbai", "India"));
        addressList.add(new Address("Cannought Palace", "Delhi", "India"));
        addressList.add(new Address("Andheri", "Mumbai", "India"));
        addressList.add(new Address("India Gate", "Delhi", "India"));
        addressList.add(new Address("Juhu", "Mumbai", "India"));

        addressRepository.save(addressList);

    }

    @Test
    public void pagination() {
        Page<Address> addressPage = addressRepository.findAll(new PageRequest(1, 2));

        List<Address> addressList = addressPage.getContent();
        for(Address address:addressList){
            System.out.println("adress street====="+address.getStreet());
        }

        assertThat(addressPage.getTotalElements(), is(10L));
        assertThat(addressPage.getTotalPages(), is(5));
        assertThat(addressList.size(), is(2));

    }
    @Test
    public void sorting() {
        Page<Address> addressPage = addressRepository.findAll(new PageRequest(0, 2,new Sort(Sort.Direction.ASC,"street")));

        List<Address> addressList = addressPage.getContent();

        assertThat(addressPage.getTotalElements(), is(10L));
        assertThat(addressPage.getTotalPages(), is(5));
        assertThat(addressList.size(), is(2));
        assertThat(addressList.get(0).getStreet(), is("Andheri"));
//        assertThat(addressList.get(1).getStreet(), is("Cannought Palace"));

    }


}
