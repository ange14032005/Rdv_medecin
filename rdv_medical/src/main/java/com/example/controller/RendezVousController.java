package com.example.controller;

import com.example.model.RendezVous;
import com.example.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    // Ajouter un rendez-vous
    @PostMapping("/ajouter")
    public RendezVous ajouterRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousService.ajouterRendezVous(rendezVous);
    }

    // Récupérer un rendez-vous par ID
    @GetMapping("/{id}")
    public Optional<RendezVous> trouverParId(@PathVariable Long id) {
        return rendezVousService.trouverParId(id);
    }

    // Récupérer tous les rendez-vous
    @GetMapping("/tous")
    public List<RendezVous> listerTous() {
        return rendezVousService.listerTous();
    }
}
