package com.example.sae.Controllers;

import com.example.sae.Models.*;
import com.example.sae.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:5173")
public class NoteController {

    @Autowired private AttestationService attestationService;
    @Autowired private UserService userService;
    @Autowired private FormationService formationService;
    @Autowired private IntervenantService intervenantService; // Vérifie que ce service existe

    @PostMapping("/saisir")
    public ResponseEntity<String> saisirNote(@RequestBody Map<String, Object> data) {
        try {
            // Conversion sécurisée des données reçues de Postman
            Long userId = Long.valueOf(data.get("userId").toString());
            Long formationId = Long.valueOf(data.get("formationId").toString());
            Long intervenantId = Long.valueOf(data.get("intervenantId").toString());
            Double note = Double.valueOf(data.get("note").toString());

            // Récupération des objets en base
            User user = userService.getById(userId);
            Formation formation = formationService.getById(formationId);
            Intervenant intervenant = intervenantService.getById(intervenantId);

            // Vérification si un objet est null pour éviter le Plantage
            if (user == null || formation == null || intervenant == null) {
                return ResponseEntity.badRequest().body("Erreur : Utilisateur, Formation ou Intervenant introuvable.");
            }

            // Appel du service pour sauvegarder et envoyer
            attestationService.validerEtEnvoyerAttestation(user, formation, intervenant, note);

            return ResponseEntity.ok("Note enregistrée et attestation envoyée avec succès !");

        } catch (Exception e) {
            e.printStackTrace(); // Affiche l'erreur précise dans la console IntelliJ
            return ResponseEntity.internalServerError().body("Erreur serveur : " + e.getMessage());
        }
    }
}*/