package com.revature.controllers;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class RegisterController {


    private UserRepository userRepo;

    @Autowired
    public RegisterController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }
        userRepo.save(user);
        return new ResponseEntity<>("User registered", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        userRepo.delete(user);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

}
