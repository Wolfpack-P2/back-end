package com.revature.controllers;

import java.util.List;

import com.revature.models.Management;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementControllerTest {

    @Autowired
    ManagementController managementController;

    @Test
    public void testGetAllManagements() {
        List<Management> managements = managementController.getAllManagements();
        for (Management management : managements) {
            assert management.getManagementId() != null;
            assert management.getPaymentType() != null;
            assert management.getCountry() != null;
            assert management.getPotentialRevenue() != null;
            assert management.getPaymentTxnSuccess() != null;
        }
    }
}
