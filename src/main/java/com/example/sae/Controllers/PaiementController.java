package com.example.sae.Controllers;

import com.example.sae.Models.Formation;
import com.example.sae.Models.Paiement;
import com.example.sae.Models.User;
import com.example.sae.Services.FormationService;
import com.example.sae.Services.StripeService;
import com.example.sae.Services.UserService;
import com.example.sae.repository.PaiementRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "http://localhost:5173")
public class PaiementController {

    @Autowired private StripeService stripeService;
    @Autowired private PaiementRepository paiementRepository;
    @Autowired private UserService userService;
    @Autowired private FormationService formationService;

    @PostMapping("/checkout")
    public Map<String, String> createCheckout(@RequestBody Map<String, Long> data) throws StripeException {
        User user = userService.getById(data.get("userId"));
        // On récupère directement la formation
        Formation formation = formationService.getById(data.get("formationId"));

        String successUrl = "http://localhost:5173/suivi";
        String cancelUrl = "http://localhost:5173/details/" + formation.getIdFormation();

        // On adapte l'appel au service (voir étape suivante)
        com.stripe.model.checkout.Session stripeSession = stripeService.createStripeSession(
                user,
                formation,
                successUrl,
                cancelUrl
        );

        Paiement paiement = new Paiement();
        paiement.setUser(user);
        paiement.setFormation(formation); // Assurez-vous d'avoir ce champ dans votre modèle Paiement
        paiement.setStatut(false);
        paiement.setDatePaiement(LocalDateTime.now());
        paiement.setStripeSessionId(stripeSession.getId());
        paiement.setMontant(formation.getPrix());

        paiementRepository.save(paiement);

        return Map.of("url", stripeSession.getUrl());
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        String endpointSecret = "whsec_3482740461bdaba26c9924e5279983634cda64e4afc281eb3ba6882e45c7184f";

        try {
            Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);

            if ("checkout.session.completed".equals(event.getType())) {
                // Utilisation du nom complet pour éviter le conflit "Session"
                com.stripe.model.checkout.Session stripeSession = (com.stripe.model.checkout.Session) event.getDataObjectDeserializer().getObject().get();

                Paiement paiement = paiementRepository.findByStripeSessionId(stripeSession.getId());
                if (paiement != null) {
                    paiement.setStatut(true);
                    paiementRepository.save(paiement);
                }
            }
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Webhook Error");
        }
    }
}
