package com.example.sae.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private String login;
    private String password;

    // Cette méthode sera surchargée par les enfants pour la redirection React
    public abstract String getRole();
}