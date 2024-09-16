package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

import java.sql.Timestamp;
@Component
@Entity
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "eccomerce_website_name")
    private String ecomWeb;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "price")
    private Double price;





}
