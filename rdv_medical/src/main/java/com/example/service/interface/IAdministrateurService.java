package com.example.service.interfaces;

import com.example.model.Administrateur;
import java.util.List;
import java.util.Optional;

public interface IAdministrateurService {
    Administrateur ajouterAdministrateur(Administrateur administrateur);
    Optional<Administrateur> trouverParId(Long id);
    List<Administrateur> listerTous();
}