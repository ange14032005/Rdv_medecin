package com.example.controller;

import com.example.config.JwtUtil;
import com.example.model.Utilisateur;
import com.example.service.interfaces.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUtilisateurService utilisateurService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getMotDePasse())
        );

        String email = loginRequest.getEmail();
        Utilisateur utilisateur = utilisateurService.trouverParEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        String role = utilisateur.getRole();
        Long userId = utilisateur.getId(); // Récupère l’ID
        String token = jwtUtil.generateToken(email, role, userId);

        return ResponseEntity.ok(new LoginResponse(token, role));
    }
}

class LoginRequest {
    private String email;
    private String motDePasse;

    // Getters et setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}

class LoginResponse {
    private String token;
    private String role;

    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // Getters
    public String getToken() { return token; }
    public String getRole() { return role; }
}