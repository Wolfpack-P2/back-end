package com.revature.controllers;

import java.util.List;

import com.revature.models.FactEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactControllerTest {

    @Autowired
    FactController factController;

    @Test
    public void testGetAllFacts() {
        List<FactEntity> facts = factController.getAllFacts();
        for (FactEntity fact : facts) {
            assert fact.getId() != null;
            assert fact.getOrderEntity() != null;
            assert fact.getCustomerEntity() != null;
            assert fact.getProductEntity() != null;
            assert fact.getPrice() != null;
            assert fact.getPaymentEntity() != null;
        }
    }
}
