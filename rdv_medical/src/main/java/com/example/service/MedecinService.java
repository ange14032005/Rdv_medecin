package com.example.service;

import com.example.model.Medecin;
import com.example.repository.MedecinRepository;
import com.example.service.interfaces.IMedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinService implements IMedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public Medecin ajouterMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Optional<Medecin> trouverParId(Long id) {
        return medecinRepository.findById(id);
    }

    @Override
    public List<Medecin> listerTous() {
        return medecinRepository.findAll();
    }
}