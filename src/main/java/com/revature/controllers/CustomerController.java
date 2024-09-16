package com.revature.controllers;

import com.revature.models.CustomerEntity;
import com.revature.models.OrderEntity;
import com.revature.repositories.CustomerTableRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerTableRepository customerTableRepository;

    public CustomerController(CustomerTableRepository customerTableRepository){
        this.customerTableRepository=customerTableRepository;
    }
    
    @GetMapping
    public List<CustomerEntity> getAllCustomers(){
        System.out.println(customerTableRepository.findAll());
        return customerTableRepository.findAll();
    }
}


