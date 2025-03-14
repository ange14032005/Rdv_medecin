package com.example.service;

import com.example.model.Creneau;
import com.example.repository.CreneauRepository;
import com.example.service.interfaces.ICreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreneauService implements ICreneauService {

    @Autowired
    private CreneauRepository creneauRepository;

    @Override
    public Creneau ajouterCreneau(Creneau creneau) {
        return creneauRepository.save(creneau);
    }

    @Override
    public Optional<Creneau> trouverParId(Long id) {
        return creneauRepository.findById(id);
    }

    @Override
    public List<Creneau> listerTous() {
        return creneauRepository.findAll();
    }

    @Override
    public List<Creneau> trouverParMedecin(Long medecinId) {
        return creneauRepository.findByMedecinId(medecinId);
    }
}