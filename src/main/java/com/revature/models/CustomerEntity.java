package com.revature.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customer_info")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerEntity {

    @Column(name = "customer_id")
    private int customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;
}

