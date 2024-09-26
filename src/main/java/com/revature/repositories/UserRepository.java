package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ETL.USERS (FIRSTNAME, LASTNAME, USERNAME, PASSWORD) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    void registerUser(String firstName, String lastName, String username, String password);

    User findByUsername(String username);

    User findById(int id);


}
