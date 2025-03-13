package com.example.service;

import com.example.model.RendezVous;
import com.example.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    // Ajouter un rendez-vous
    public RendezVous ajouterRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    // Récupérer un rendez-vous par son ID
    public Optional<RendezVous> trouverParId(Long id) {
        return rendezVousRepository.findById(id);
    }

    // Récupérer tous les rendez-vous
    public List<RendezVous> listerTous() {
        return rendezVousRepository.findAll();
    }
}
