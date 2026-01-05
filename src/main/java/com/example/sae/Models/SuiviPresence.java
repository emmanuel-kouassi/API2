package com.example.sae.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

// SuiviPresence.java
@Entity
@Data
public class SuiviPresence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresence;
    private LocalDate dateJour;
    private boolean present;
    private String emargementNumerique;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;
}
