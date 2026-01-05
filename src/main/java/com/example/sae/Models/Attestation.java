package com.example.sae.Models;

import jakarta.persistence.*;
import lombok.Data;

// Attestation.java
@Entity
@Data
public class Attestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAttestation;
    private Double note;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    private Formation formation;

   // @ManyToOne
   // @JoinColumn(name = "id_intervenant")
   // private Intervenant intervenant;
}
