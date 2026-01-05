package com.example.sae.repository;

import com.example.sae.Models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    // Pour trouver une inscription spécifique afin de l'annuler
    Optional<Inscription> findByUserIdAndSessionIdSession(Long userId, Long sessionId);

    // Supprime l'inscription (nécessite @Transactional)
    @Transactional
    void deleteByUserIdAndSessionIdSession(Long userId, Long sessionId);
}