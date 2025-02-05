package com.cybersecurity.service;

import com.cybersecurity.model.User;
import com.cybersecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public boolean authenticateUser(User user){
        User existingUser = userRepository.findByUsername(user.getUsername());
        return existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword());

    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
