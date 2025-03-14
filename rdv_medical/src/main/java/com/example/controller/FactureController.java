package com.example.controller;

import com.example.model.Facture;
import com.example.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @PostMapping("/ajouter")
    public Facture ajouterFacture(@RequestBody Facture facture) {
        return factureService.ajouterFacture(facture);
    }

    @GetMapping("/{id}")
    public Optional<Facture> trouverParId(@PathVariable Long id) {
        return factureService.trouverParId(id);
    }

    @GetMapping("/tous")
    public List<Facture> listerTous() {
        return factureService.listerTous();
    }

    @GetMapping("/patient/{patientId}")
    public List<Facture> trouverParPatient(@PathVariable Long patientId) {
        return factureService.trouverParPatient(patientId);
    }
}