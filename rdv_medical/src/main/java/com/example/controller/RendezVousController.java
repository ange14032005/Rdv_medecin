package com.example.controller;

import com.example.model.RendezVous;
import com.example.service.interfaces.IRendezVousService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rendezvous")
@Tag(name = "RendezVous", description = "API pour gérer les rendez-vous")
public class RendezVousController {

    @Autowired
    private IRendezVousService rendezVousService;

    @PostMapping("/ajouter")
    @Operation(summary = "Ajouter un nouveau rendez-vous", description = "Crée un rendez-vous avec validation du créneau.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Rendez-vous créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Erreur de validation (ex. créneau pris)")
    })
    public ResponseEntity<?> ajouterRendezVous(@RequestBody RendezVous rendezVous) {
        try {
            RendezVous savedRdv = rendezVousService.ajouterRendezVous(rendezVous);
            return new ResponseEntity<>(savedRdv, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un rendez-vous par ID")
    @ApiResponse(responseCode = "200", description = "Rendez-vous trouvé")
    public ResponseEntity<Optional<RendezVous>> trouverParId(@PathVariable Long id) {
        Optional<RendezVous> rendezVous = rendezVousService.trouverParId(id);
        return ResponseEntity.ok(rendezVous);
    }

    @GetMapping("/tous")
    @Operation(summary = "Lister tous les rendez-vous")
    @ApiResponse(responseCode = "200", description = "Liste des rendez-vous")
    public ResponseEntity<List<RendezVous>> listerTous() {
        List<RendezVous> rendezVous = rendezVousService.listerTous();
        return ResponseEntity.ok(rendezVous);
    }

    @PutMapping("/{id}/annuler")
    @Operation(summary = "Annuler un rendez-vous", description = "Met le statut à ANNULE.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rendez-vous annulé"),
        @ApiResponse(responseCode = "400", description = "Erreur (ex. déjà annulé)")
    })
    public ResponseEntity<?> annulerRendezVous(@PathVariable Long id) {
        try {
            rendezVousService.annulerRendezVous(id);
            return ResponseEntity.ok("Rendez-vous annulé avec succès.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/confirmer")
    @Operation(summary = "Confirmer un rendez-vous", description = "Met le statut à CONFIRME.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rendez-vous confirmé"),
        @ApiResponse(responseCode = "400", description = "Erreur (ex. déjà confirmé)")
    })
    public ResponseEntity<?> confirmerRendezVous(@PathVariable Long id) {
        try {
            rendezVousService.confirmerRendezVous(id);
            return ResponseEntity.ok("Rendez-vous confirmé avec succès.");
        } catch (Exception e) {
            return new ResponseEntity<>(e grillee.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}