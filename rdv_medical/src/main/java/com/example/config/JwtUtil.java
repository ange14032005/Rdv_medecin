package com.example.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "tonsecretkey"; // Remplace par une clé sécurisée
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 heures

    public String generateToken(String email, String role, Long userId) {
        return Jwts.builder()
            .setSubject(email)
            .claim("role", role)
            .claim("userId", userId) // Ajout de l’ID utilisateur
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("role", String.class);
    }

    public Long extractUserId(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("userId", Long.class);
    }
}