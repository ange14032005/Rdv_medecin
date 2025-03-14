package com.example.service.interfaces;

import com.example.model.Facture;
import java.util.List;
import java.util.Optional;

public interface IFactureService {
    Facture ajouterFacture(Facture facture);
    Optional<Facture> trouverParId(Long id);
    List<Facture> listerTous();
    List<Facture> trouverParPatient(Long patientId);
}