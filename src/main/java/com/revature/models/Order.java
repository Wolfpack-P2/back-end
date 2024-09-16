package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity 
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_category")
    private String productCategory;
    @Column(name = "payment_type")
    private String paymentType;
    private int qty;
    private Double price;
    //private LocalDateTime datetime;
    private String country;
    private String city;
  
    @Column(name = "ecommerce_website_name")
    private String ecommerceWebsiteName;
    @Column(name = "payment_txn_success")
    private Character paymentTxnSuccess;
    @Column(name = "failure_reason")
    private String failureReason;

    @Transient
    private int count;

}

