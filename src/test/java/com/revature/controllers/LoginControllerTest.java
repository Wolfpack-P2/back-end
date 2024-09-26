package com.revature.controllers;

import com.revature.models.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        User u = loginController.login(user);
        assert u != null;
    }
}