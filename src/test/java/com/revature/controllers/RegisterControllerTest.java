package com.revature.controllers;


import com.revature.models.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterControllerTest {

    @Autowired
    private RegisterController registerController;

    @Test
    public void registerUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");
        ResponseEntity<String> response = registerController.registerUser(user);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void registerUserDuplicateUsername() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");
        ResponseEntity<String> response = registerController.registerUser(user);
        assert response.getStatusCode() == HttpStatus.CONFLICT;
    }

    @Test
    public void deleteUser() {
        ResponseEntity<String> response = registerController.deleteUser("test");
        assert response.getStatusCode() == HttpStatus.OK;
    }
}