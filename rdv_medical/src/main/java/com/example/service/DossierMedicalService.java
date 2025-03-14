package com.example.service;

import com.example.model.DossierMedical;
import com.example.repository.DossierMedicalRepository;
import com.example.service.interfaces.IDossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DossierMedicalService implements IDossierMedicalService {

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Override
    public DossierMedical ajouterDossier(DossierMedical dossier) {
        return dossierMedicalRepository.save(dossier);
    }

    @Override
    public Optional<DossierMedical> trouverParId(Long id) {
        return dossierMedicalRepository.findById(id);
    }

    @Override
    public List<DossierMedical> listerTous() {
        return dossierMedicalRepository.findAll();
    }
}