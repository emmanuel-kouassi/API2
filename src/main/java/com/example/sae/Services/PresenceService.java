package com.example.sae.Services;

import com.example.sae.Models.SuiviPresence;
import com.example.sae.repository.SuiviPresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresenceService {
    @Autowired
    private SuiviPresenceRepository presenceRepository;

    public SuiviPresence validerPresence(SuiviPresence presence) {
        return presenceRepository.save(presence);
    }

    public List<SuiviPresence> HistoriqueParUtilisateur(Long userId) {
        // Logique pour récupérer tout l'historique d'un élève
        return presenceRepository.findAll().stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .toList();
    }
}
