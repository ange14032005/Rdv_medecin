package com.example.controller;

import com.example.model.Utilisateur;
import com.example.service.interfaces.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @PostMapping("/ajouter")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.ajouterUtilisateur(utilisateur);
    }

    @GetMapping("/{id}")
    public Optional<Utilisateur> trouverParId(@PathVariable Long id) {
        return utilisateurService.trouverParId(id);
    }

    @GetMapping("/email")
    public Optional<Utilisateur> trouverParEmail(@RequestParam String email) {
        return utilisateurService.trouverParEmail(email);
    }
}