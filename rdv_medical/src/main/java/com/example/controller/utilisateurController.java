package com.example.controller;

import com.example.model.Utilisateur;
import com.example.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Ajouter un nouvel utilisateur
    @PostMapping("/ajouter")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.ajouterUtilisateur(utilisateur);
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public Optional<Utilisateur> trouverParId(@PathVariable Long id) {
        return utilisateurService.trouverParId(id);
    }

    // Récupérer un utilisateur par email
    @GetMapping("/email")
    public Optional<Utilisateur> trouverParEmail(@RequestParam String email) {
        return utilisateurService.trouverParEmail(email);
    }
}
