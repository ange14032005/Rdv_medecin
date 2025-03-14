package com.example.service;

import com.example.model.RendezVous;
import com.example.model.Creneau;
import com.example.model.Medecin;
import com.example.model.Patient;
import com.example.repository.RendezVousRepository;
import com.example.service.interfaces.IRendezVousService;
import com.example.service.interfaces.ICreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class RendezVousService implements IRendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private ICreneauService creneauService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public RendezVous ajouterRendezVous(RendezVous rendezVous) throws Exception {
        // Validation 1 : Vérifier si le créneau est déjà pris
        Long creneauId = rendezVous.getCreneau().getId();
        String date = rendezVous.getDate();
        List<RendezVous> existingRdv = rendezVousRepository.findByCreneauIdAndDate(creneauId, date);
        if (!existingRdv.isEmpty()) {
            throw new Exception("Ce créneau est déjà pris pour cette date.");
        }

        // Validation 2 : Vérifier que la date correspond au jour du créneau
        Creneau creneau = creneauService.trouverParId(creneauId)
            .orElseThrow(() -> new Exception("Créneau introuvable."));
        String jourCreneau = creneau.getJour().toUpperCase(); // Ex. "LUNDI"
        LocalDate rdvDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE); // Ex. "2025-03-17"
        DayOfWeek jourDate = rdvDate.getDayOfWeek();
        if (!jourCreneau.equals(jourDate.name())) {
            throw new Exception("La date (" + jourDate + ") ne correspond pas au jour du créneau (" + jourCreneau + ").");
        }

        // Sauvegarder le rendez-vous
        RendezVous savedRdv = rendezVousRepository.save(rendezVous);

        // Envoyer les notifications par email (asynchrone)
        envoyerNotificationAsync(savedRdv);

        return savedRdv;
    }

@Override
    public Optional<RendezVous> trouverParId(Long id) {
        return rendezVousRepository.findById(id);
    }

    @Override
    public List<RendezVous> listerTous() {
        return rendezVousRepository.findAll();
    }

    @Override
    public void annulerRendezVous(Long id) throws Exception {
        RendezVous rdv = rendezVousRepository.findById(id)
            .orElseThrow(() -> new Exception("Rendez-vous introuvable."));
        if ("ANNULE".equals(rdv.getStatut())) {
            throw new Exception("Ce rendez-vous est déjà annulé.");
        }
        rdv.setStatut("ANNULE");
        rendezVousRepository.save(rdv);
        envoyerNotificationAsync(rdv); // Notifie l’annulation
    }

    @Override
    public void confirmerRendezVous(Long id) throws Exception {
        RendezVous rdv = rendezVousRepository.findById(id)
            .orElseThrow(() -> new Exception("Rendez-vous introuvable."));
        if ("CONFIRME".equals(rdv.getStatut())) {
            throw new Exception("Ce rendez-vous est déjà confirmé.");
        }
        if ("ANNULE".equals(rdv.getStatut())) {
            throw new Exception("Un rendez-vous annulé ne peut pas être confirmé.");
        }
        rdv.setStatut("CONFIRME");
        rendezVousRepository.save(rdv);
        envoyerNotificationAsync(rdv); // Notifie la confirmation
    }

    @Async
    private void envoyerNotificationAsync(RendezVous rendezVous) {
        Patient patient = rendezVous.getPatient();
        Medecin medecin = rendezVous.getMedecin();
        Creneau creneau = rendezVous.getCreneau();

        // Email au patient
        String patientEmail = patient.getUtilisateur().getEmail();
        String patientSubject = "Confirmation de votre rendez-vous";
        String patientMessage = String.format(
            "Votre rendez-vous avec le Dr %s est confirmé pour le %s de %s à %s.",
            medecin.getUtilisateur().getNom(),
            rendezVous.getDate(),
            creneau.getHeureDebut(),
            creneau.getHeureFin()
        );
        sendHtmlEmail(patientEmail, patientSubject, patientMessage, patient.getUtilisateur().getNom());

        // Email au médecin
        String medecinEmail = medecin.getUtilisateur().getEmail();
        String medecinSubject = "Nouveau rendez-vous programmé";
        String medecinMessage = String.format(
            "Un rendez-vous avec %s est programmé pour le %s de %s à %s.",
            patient.getUtilisateur().getNom(),
            rendezVous.getDate(),
            creneau.getHeureDebut(),
            creneau.getHeureFin()
        );
        sendHtmlEmail(medecinEmail, medecinSubject, medecinMessage, medecin.getUtilisateur().getNom());
    }

    private void sendHtmlEmail(String to, String subject, String message, String recipientName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("tonemail@gmail.com"); // Remplace par ton email

            // Préparer le contexte pour Thymeleaf
            Context context = new Context();
            context.setVariable("subject", subject);
            context.setVariable("recipientName", recipientName);
            context.setVariable("message", message);

            // Générer le contenu HTML
            String htmlContent = templateEngine.process("email-template", context);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // À remplacer par une gestion d’erreur appropriée en production
        }
    }
}