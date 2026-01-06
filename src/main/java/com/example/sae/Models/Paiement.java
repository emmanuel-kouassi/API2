package com.example.sae.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    private Double montant;
    private boolean statut; // false par défaut
    private LocalDateTime datePaiement;
    private String stripeSessionId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    // On remplace ou on ajoute le lien vers Formation
    @ManyToOne
    @JoinColumn(name = "id_formation")
    private Formation formation;
}