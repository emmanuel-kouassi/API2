package com.example.sae.repository;

import com.example.sae.Models.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByUserId(Long userId);
    // Cette méthode sera utile plus tard pour le Webhook
    Paiement findByStripeSessionId(String stripeSessionId);
}