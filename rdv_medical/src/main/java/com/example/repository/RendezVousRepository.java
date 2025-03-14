package com.example.repository;

import com.example.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByCreneauIdAndDate(Long creneauId, String date);
}