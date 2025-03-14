package com.example.service;

import com.example.model.Administrateur;
import com.example.repository.AdministrateurRepository;
import com.example.service.interfaces.IAdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurService implements IAdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Override
    public Administrateur ajouterAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    @Override
    public Optional<Administrateur> trouverParId(Long id) {
        return administrateurRepository.findById(id);
    }

    @Override
    public List<Administrateur> listerTous() {
        return administrateurRepository.findAll();
    }
}