package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="fact_table1")

public class FactEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToOne
    @JoinColumn(name="order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name="customer_name")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name="product_name")
    private ProductEntity productEntity;

    @Column(name="price")
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name="payement_txn_id")
    private PaymentEntity payementEntity;


}
