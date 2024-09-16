package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories("com.revature.repositories")
@EntityScan("com.revature.models")
@ComponentScan("com.revature")
@EnableTransactionManagement
public class EtlECommerceApplication {


    public static void main(String[] args) {
        SpringApplication.run(EtlECommerceApplication.class, args);
    }

}
