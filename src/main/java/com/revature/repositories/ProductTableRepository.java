package com.revature.repositories;

import com.revature.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTableRepository extends JpaRepository<ProductEntity,String> {
}
