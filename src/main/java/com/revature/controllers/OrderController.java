package com.revature.controllers;

import com.revature.models.Order;
import com.revature.models.QueryResult;
import com.revature.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @GetMapping("/category")
    public List<QueryResult> topCategory(){
        System.out.println(orderRepo.findCategory());
        return orderRepo.findCategory();
    }

    @GetMapping("/category/country")
    public  List<QueryResult> topCategoryByCountry(){
        return orderRepo.findCategoryCountry();
    }

    @GetMapping("/year")
    public List<QueryResult> yearPopularity(){
        return orderRepo.findYearPopularity();
    }

    @GetMapping("/year/country")
    public List<QueryResult> yearPopularityByCountry(){
        return orderRepo.findYearPopularityCountry();
    }

    @GetMapping("/sales/country")
    public List<QueryResult> topCountry(){
        return orderRepo.findTopCountry();
    }

    @GetMapping("/time")
    public List<QueryResult> busiestTime(){
        return orderRepo.findBusiestTime();
    }

    @GetMapping("/time/country")
    public List<QueryResult> busiestTimeByCountry(){
        return orderRepo.findBusiestTimeCountry();
    }

    @GetMapping("/paymenttype")
    public List<Order> findAll(){
        List<Order> all = (List<Order>) orderRepo.findAll();
        return all;

        // display payment types with JS
    }




}
