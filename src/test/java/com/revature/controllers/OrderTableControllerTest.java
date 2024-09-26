package com.revature.controllers;

import java.util.List;

import com.revature.models.OrderEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTableControllerTest {

    @Autowired
    private OrderTableController orderTableController;

    @Test
    public void testGetAllOrders() {
        List<OrderEntity> orders = orderTableController.getAllOrders();
        for (OrderEntity order : orders) {
            assert order.getOrderId() != null;
            assert order.getProductName() != null;
            assert order.getEcomWeb() != null;
            assert order.getOrderDate() != null;
            assert order.getPrice() != null;
            assert order.getQty() != null;
        }
    }
}
