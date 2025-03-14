package com.example.controller;

import com.example.model.Patient;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/ajouter")
    public Patient ajouterPatient(@RequestBody Patient patient) {
        return patientService.ajouterPatient(patient);
    }

    @GetMapping("/{id}")
    public Optional<Patient> trouverParId(@PathVariable Long id) {
        return patientService.trouverParId(id);
    }

    @GetMapping("/tous")
    public List<Patient> listerTous() {
        return patientService.listerTous();
    }
}