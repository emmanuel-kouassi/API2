package com.example.sae.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formation")
    private Long idFormation;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer dureeJour;

    private Double prix;

    private String categorie;

    private Integer placeMax;

    private Integer placeOccupe;

    @Column(columnDefinition = "LONGTEXT")
    private String formationImage; // Stocke l'image en Base64

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Session> sessions;

    // Getter spécifique si nécessaire (Lombok @Data s'en occupe normalement)
    public Double getPrix() {
        return this.prix;
    }
}