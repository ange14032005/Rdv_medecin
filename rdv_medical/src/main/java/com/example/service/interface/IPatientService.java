package com.example.service.interfaces;

import com.example.model.Patient;
import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient ajouterPatient(Patient patient);
    Optional<Patient> trouverParId(Long id);
    List<Patient> listerTous();
}