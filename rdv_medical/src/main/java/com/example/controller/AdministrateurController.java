package com.example.controller;

import com.example.model.Administrateur;
import com.example.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @PostMapping("/ajouter")
    public Administrateur ajouterAdministrateur(@RequestBody Administrateur administrateur) {
        return administrateurService.ajouterAdministrateur(administrateur);
    }

    @GetMapping("/{id}")
    public Optional<Administrateur> trouverParId(@PathVariable Long id) {
        return administrateurService.trouverParId(id);
    }

    @GetMapping("/tous")
    public List<Administrateur> listerTous() {
        return administrateurService.listerTous();
    }
}