package com.revature.repositories;

import com.revature.models.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTableRepository extends JpaRepository<PaymentEntity,Integer> {

}
