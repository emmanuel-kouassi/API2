package com.example.sae.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscription;

    private String nom;
    private String prenom;

    @Column(name = "adresse_postale")
    private String adressePostale;

    @Column(name = "ville_region")
    private String villeRegion;

    private String mail;
    private String telephone;
    private String login;
    private String password;
    @Column(name = "date_naissance")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    private LocalDate dateInscription;

    // Clé étrangère vers l'utilisateur
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_formation", referencedColumnName = "id_formation") // Correspond à l'ID dans ton image formation
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "id_session", referencedColumnName = "id_session")
    private Session session;

    @Entity
    @Data
    public static class Attestation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_attestation")
        private Long idAttestation;

        private Double note; //

        @ManyToOne
        @JoinColumn(name = "id_formation") //
        private Formation formation;

        @ManyToOne
        @JoinColumn(name = "id_user") //
        private User user;

       // @ManyToOne
       // @JoinColumn(name = "id_intervenant") // Nouvelle colonne pour savoir qui a noté
       // private Intervenant intervenant;
    }
}