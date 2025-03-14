package com.example.service;

import com.example.model.Patient;
import com.example.repository.PatientRepository;
import com.example.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient ajouterPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> trouverParId(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> listerTous() {
        return patientRepository.findAll();
    }
}