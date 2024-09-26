package com.revature.controllers;

import com.revature.models.ProductEntity;
import com.revature.repositories.ProductTableRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductTableRepository productTableRepository;

    public ProductController(ProductTableRepository productTableRepository){
        this.productTableRepository=productTableRepository;
    }
    @GetMapping
    public List<ProductEntity> getAllProducts(){
        System.out.println(productTableRepository.findAll());
        return productTableRepository.findAll();
    }
}
