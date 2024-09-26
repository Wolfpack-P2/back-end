package com.revature.controllers;

import com.revature.models.FactEntity;
import com.revature.repositories.FactTableRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facts")
public class FactController {

    FactTableRepository factTableRepository;

    public FactController(FactTableRepository factTableRepository){
        this.factTableRepository=factTableRepository;
    }

    @GetMapping
    public List<FactEntity> getAllFacts(){
        System.out.println(factTableRepository.findAll());
        return factTableRepository.findAll();
    }
}
