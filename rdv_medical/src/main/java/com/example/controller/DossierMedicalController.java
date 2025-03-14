package com.example.controller;

import com.example.model.DossierMedical;
import com.example.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dossiers")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @PostMapping("/ajouter")
    public DossierMedical ajouterDossier(@RequestBody DossierMedical dossier) {
        return dossierMedicalService.ajouterDossier(dossier);
    }

    @GetMapping("/{id}")
    public Optional<DossierMedical> trouverParId(@PathVariable Long id) {
        return dossierMedicalService.trouverParId(id);
    }

    @GetMapping("/tous")
    public List<DossierMedical> listerTous() {
        return dossierMedicalService.listerTous();
    }
}