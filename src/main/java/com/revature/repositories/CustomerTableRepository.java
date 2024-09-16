package com.revature.repositories;

import com.revature.models.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTableRepository extends JpaRepository<CustomerEntity,Integer> {


}
