package com.revature.repositories;

import com.revature.models.*;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface FactTableRepository extends JpaRepository<FactEntity, Integer> {

}
