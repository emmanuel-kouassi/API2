package com.example.sae.Services;

import com.example.sae.Models.Paiement;
import com.example.sae.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

    public Paiement enregistrerPaiement(Paiement paiement) {
        paiement.setDatePaiement(LocalDateTime.now());
        return paiementRepository.save(paiement);
    }
}
