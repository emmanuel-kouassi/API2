package com.example.sae.repository;

import com.example.sae.Models.SuiviPresence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuiviPresenceRepository extends JpaRepository<SuiviPresence, Long> {
    // Trouver les présences d'un utilisateur pour une session donnée
    List<SuiviPresence> findByUser_IdAndSession_IdSession(Long userId, Long sessionId);
}