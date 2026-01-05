package com.example.sae.repository;

import com.example.sae.Models.Personne; // Vérifie bien le nom de ton modèle parent
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    // Cette méthode permettra de trouver n'importe qui par mail
    Optional<Personne> findByMail(String mail);
}