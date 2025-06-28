package com.example.demo.service;


import com.example.demo.entity.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUser(Long id);
}
