package com.revature.controllers;

import java.util.List;

import com.revature.models.Management;
import com.revature.repositories.ManagementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    ManagementRepository managementRepository;

    @RequestMapping
    public List<Management> getAllManagements() {
        return managementRepository.findAll();
    }
    
}
