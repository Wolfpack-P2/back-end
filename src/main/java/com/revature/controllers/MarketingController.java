package com.revature.controllers;

import java.util.List;

import com.revature.models.Marketing;
import com.revature.repositories.MarketingRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/marketing")
public class MarketingController {

    @Autowired
    MarketingRepository marketingRepository;

    @GetMapping
    public List<Marketing> getAllMarkets() {
        System.out.println(marketingRepository.findAll());
        return marketingRepository.findAll();
    }
    
}
