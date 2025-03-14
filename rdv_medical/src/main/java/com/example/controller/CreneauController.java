package com.example.controller;

import com.example.model.Creneau;
import com.example.service.CreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creneaux")
public class CreneauController {

    @Autowired
    private CreneauService creneauService;

    @PostMapping("/ajouter")
    public Creneau ajouterCreneau(@RequestBody Creneau creneau) {
        return creneauService.ajouterCreneau(creneau);
    }

    @GetMapping("/{id}")
    public Optional<Creneau> trouverParId(@PathVariable Long id) {
        return creneauService.trouverParId(id);
    }

    @GetMapping("/tous")
    public List<Creneau> listerTous() {
        return creneauService.listerTous();
    }

    @GetMapping("/medecin/{medecinId}")
    public List<Creneau> trouverParMedecin(@PathVariable Long medecinId) {
        return creneauService.trouverParMedecin(medecinId);
    }
}