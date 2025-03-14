package com.example.service.interfaces;

import com.example.model.Utilisateur;
import java.util.List;
import java.util.Optional;

public interface IUtilisateurService {
    Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
    Optional<Utilisateur> trouverParId(Long id);
    Optional<Utilisateur> trouverParEmail(String email);
    List<Utilisateur> listerTous();
}