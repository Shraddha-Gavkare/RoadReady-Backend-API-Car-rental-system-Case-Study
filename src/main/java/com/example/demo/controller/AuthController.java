package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterUserDTO;
 
import com.example.demo.entity.*;
 
import com.example.demo.security.*;
 
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO dto) {
        if (userService.getUserByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }

        User newUser = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .role("CUSTOMER")
                .build();

        User savedUser = userService.registerUser(newUser);
        return ResponseEntity.ok(savedUser);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userService.getUserByEmail(request.getEmail());

        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        User user = userOpt.get();
        String token = jwtProvider.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token, user.getRole()));
    }
     
}
