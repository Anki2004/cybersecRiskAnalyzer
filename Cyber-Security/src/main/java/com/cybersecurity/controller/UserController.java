package com.cybersecurity.controller;

import com.cybersecurity.model.User;
import com.cybersecurity.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        boolean isAuthenticated = userService.authenticateUser(user);
        if(isAuthenticated){
            return ResponseEntity.ok("Login Successfull!");
        }else{
            return ResponseEntity.status(404).body("Invalid Username or Password");
        }
    }

}
