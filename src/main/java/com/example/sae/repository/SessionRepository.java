package com.example.sae.repository;

// On pointe vers le bon dossier "models"
import com.example.sae.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    // Si ton entité Session a bien un objet "Formation" à l'intérieur :
    List<Session> findByFormation_IdFormation(Long idFormation);
}