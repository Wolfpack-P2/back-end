package com.revature.repositories;

import com.revature.models.*;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.math.BigDecimal;

@Repository
public interface FactTableRepository extends JpaRepository<FactEntity, Integer> {

}
