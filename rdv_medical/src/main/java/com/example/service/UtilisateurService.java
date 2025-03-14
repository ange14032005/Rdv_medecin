package com.example.service;

import com.example.model.Utilisateur;
import com.example.repository.UtilisateurRepository;
import com.example.service.interfaces.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder().encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> trouverParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public Optional<Utilisateur> trouverParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public List<Utilisateur> listerTous() {
        return utilisateurRepository.findAll();
    }
}