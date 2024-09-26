package com.revature.controllers;

import com.revature.models.OrderEntity;
import com.revature.repositories.OrderTableRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderTableController {

    OrderTableRepository orderTableRepository;

    public OrderTableController(OrderTableRepository orderTableRepository){
        this.orderTableRepository=orderTableRepository;
    }
    
    @GetMapping
    public List<OrderEntity> getAllOrders(){
        System.out.println(orderTableRepository.findAll());
        return orderTableRepository.findAll();
    }
}
