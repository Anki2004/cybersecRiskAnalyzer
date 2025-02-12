package com.cybersecurity.service;

import com.cybersecurity.model.User;
import com.cybersecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        return existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword());

    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
