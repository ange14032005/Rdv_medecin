package com.example.service.interfaces;

import com.example.model.DossierMedical;
import java.util.List;
import java.util.Optional;

public interface IDossierMedicalService {
    DossierMedical ajouterDossier(DossierMedical dossier);
    Optional<DossierMedical> trouverParId(Long id);
    List<DossierMedical> listerTous();
}