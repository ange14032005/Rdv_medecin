package com.example.service;

import com.example.model.Facture;
import com.example.repository.FactureRepository;
import com.example.service.interfaces.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureService implements IFactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Override
    public Facture ajouterFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Optional<Facture> trouverParId(Long id) {
        return factureRepository.findById(id);
    }

    @Override
    public List<Facture> listerTous() {
        return factureRepository.findAll();
    }

    @Override
    public List<Facture> trouverParPatient(Long patientId) {
        return factureRepository.findByPatientId(patientId);
    }
}