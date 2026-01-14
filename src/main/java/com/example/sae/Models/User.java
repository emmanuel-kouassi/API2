package com.example.sae.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore; // N'oublie pas l'import !
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "adresse_postale")
    private String adressePostale;
    @Column(name = "ville_region")
    private String villeRegion;
    private String telephone;
    private String role;

    @Column(columnDefinition = "LONGTEXT")
    private String userpp;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Inscription> inscriptions = new ArrayList<>();
}