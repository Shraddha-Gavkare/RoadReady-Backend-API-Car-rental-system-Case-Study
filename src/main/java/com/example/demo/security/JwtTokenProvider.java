package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

 
@Value("${jwt.secret}")
private String jwtSecret;

@Value("${jwt.expiration}")
private long jwtExpiration;

private Key key;

@PostConstruct
public void init() {
    // Convert secret key to a cryptographic key
    this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
}

public String generateToken(User user) {
    return Jwts.builder()
            .setSubject(user.getEmail())
            .claim("role", user.getRole()) // Embed role in token
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
}

public String getEmailFromToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
}

public String getRoleFromToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("role", String.class);
}

public boolean validateToken(String token) {
    try {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return true;
    } catch (JwtException e) {
        return false;
    }
}
}