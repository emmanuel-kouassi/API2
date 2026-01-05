package com.example.sae.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Data @EqualsAndHashCode(callSuper = true)
public class Intervenant extends Personne {
    private String entreprise;
    private String specialite;
    private String status;
    private String telephone;
    // Suppression du champ mail (déjà dans Personne)

    @OneToMany(mappedBy = "intervenant")
    private List<Session> sessions;

    @Override
    public String getRole() {
        return "INTERVENANT";
    }
}