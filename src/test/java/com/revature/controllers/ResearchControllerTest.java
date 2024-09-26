package com.revature.controllers;

import java.util.List;

import com.revature.models.Research;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResearchControllerTest {

    @Autowired
    ResearchController researchController;

    @Test
    public void testGetAllResearch() {
        List<Research> research = researchController.getAllResearch();
        for (Research r : research) {
            assert r.getResearchId() != null;
            assert r.getQty() != null;
            assert r.getOrderDate() != null;
            assert r.getPaymentTxnSuccess() != null;
            assert r.getCountry() != null;
            assert r.getTotalPrice() != null;
            assert r.getDayOfWeek() != null;
        }

    }
}
