package com.example.service.interfaces;

import com.example.model.Medecin;
import java.util.List;
import java.util.Optional;

public interface IMedecinService {
    Medecin ajouterMedecin(Medecin medecin);
    Optional<Medecin> trouverParId(Long id);
    List<Medecin> listerTous();
}