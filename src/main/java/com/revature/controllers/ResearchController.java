package com.revature.controllers;

import java.util.List;

import com.revature.models.Research;
import com.revature.repositories.ResearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/research")
public class ResearchController {

    @Autowired
    ResearchRepository researchRepository;

    @RequestMapping
    public List<Research> getAllResearch() {
        return researchRepository.findAll();
    }
    
}
