package com.revature.controllers;

import java.util.List;

import com.revature.models.Marketing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketingControllerTest {

    @Autowired
    private MarketingController marketingController;

    @Test
    public void testGetAllMarkets() {
        List<Marketing> markets = marketingController.getAllMarkets();
        for(Marketing market : markets) {
            assert market.getMarketingId() != null;
            assert market.getProductCategory() != null;
            assert market.getQty() != null;
            assert market.getOrderDate() != null;
            assert market.getCountry() != null;
            assert market.getCity() != null;
            assert market.getTimeOfDay() != null;
            assert market.getMonths() != null;
        }

    }
}
