package com.example.service.interfaces;

import com.example.model.Creneau;
import java.util.List;
import java.util.Optional;

public interface ICreneauService {
    Creneau ajouterCreneau(Creneau creneau);
    Optional<Creneau> trouverParId(Long id);
    List<Creneau> listerTous();
    List<Creneau> trouverParMedecin(Long medecinId);
}