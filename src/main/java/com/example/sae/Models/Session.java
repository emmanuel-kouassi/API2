package com.example.sae.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "user")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Long idSession;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String lieu;
    private Integer nbPlacesMax;
    private Integer nbPlacesDispo;

    @ManyToOne
    @JoinColumn(name = "id_user") // Nom de la colonne dans votre table SQL
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    @JsonIgnoreProperties("sessions")
    private Formation formation;


    @ManyToOne
    @JoinColumn(name = "id_intervenant")
    private Intervenant intervenant;

    @OneToMany(mappedBy = "session")
    private List<SuiviPresence> presences;

    // SUPPRIME la méthode getIntervenant() manuelle qui renvoie null
    // @Data s'occupera de générer un vrai getter et un vrai setter
}