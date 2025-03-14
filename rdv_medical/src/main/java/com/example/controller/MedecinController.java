package com.example.controller;

import com.example.model.Medecin;
import com.example.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;

    @PostMapping("/ajouter")
    public Medecin ajouterMedecin(@RequestBody Medecin medecin) {
        return medecinService.ajouterMedecin(medecin);
    }

    @GetMapping("/{id}")
    public Optional<Medecin> trouverParId(@PathVariable Long id) {
        return medecinService.trouverParId(id);
    }

    @GetMapping("/tous")
    public List<Medecin> listerTous() {
        return medecinService.listerTous();
    }
}