package com.example.sae.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Paiement.java
@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    private Double montant;
    private boolean statut; // true = effectué
    private LocalDateTime datePaiement;

    // Nouveau : Stocker l'identifiant de transaction Stripe
    private String stripeSessionId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    // Ajout du lien vers la session payée
    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;
}
