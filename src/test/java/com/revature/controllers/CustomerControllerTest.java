package com.revature.controllers;

import java.util.List;

import com.revature.models.CustomerEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @Test
    public void testGetAllCustomers() {
        List<CustomerEntity> customers = customerController.getAllCustomers();
        for (CustomerEntity customer : customers) {
            assert customer.getCustomerName() != null;
            assert customer.getCity() != null;
            assert customer.getCountry() != null;
        }
    }
}
