package com.example.sae.repository;

import com.example.sae.Models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttestationRepository extends JpaRepository<Inscription.Attestation, Long> {
    // Vérifier si un utilisateur a déjà une attestation pour une formation
    Optional<Inscription.Attestation> findByUser_IdAndFormation_IdFormation(Long userId, Long formationId);
}
