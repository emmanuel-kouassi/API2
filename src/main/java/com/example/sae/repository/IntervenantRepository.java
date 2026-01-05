package com.example.sae.repository;

import com.example.sae.Models.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervenantRepository extends JpaRepository<Intervenant, Long> {
    List<Intervenant> findBySpecialite(String specialite);
}
