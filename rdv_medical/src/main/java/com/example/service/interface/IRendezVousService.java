package com.example.service.interfaces;

import com.example.model.RendezVous;
import java.util.List;
import java.util.Optional;

public interface IRendezVousService {
    RendezVous ajouterRendezVous(RendezVous rendezVous) throws Exception;
    Optional<RendezVous> trouverParId(Long id);
    List<RendezVous> listerTous();
    void annulerRendezVous(Long id) throws Exception;
    void confirmerRendezVous(Long id) throws Exception;
}