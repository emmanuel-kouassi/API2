package com.example.sae.Services;

import com.example.sae.Models.Intervenant;
import com.example.sae.repository.IntervenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntervenantService {
    @Autowired
    private IntervenantRepository intervenantRepository;
    // modifier infos
    public Intervenant update(Long id, Intervenant details) {
        Intervenant intervenant = intervenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervenant non trouvé"));

        // Mise à jour des champs (adaptez selon les attributs de votre classe Intervenant)
        intervenant.setNom(details.getNom());
        intervenant.setPrenom(details.getPrenom());
        intervenant.setMail(details.getMail());
        intervenant.setTelephone(details.getTelephone());
        intervenant.setSpecialite(details.getSpecialite());

        return intervenantRepository.save(intervenant);
    }

    public Intervenant updateStatus(Long id, String newStatus) {
        Intervenant i = intervenantRepository.findById(id).get();
        i.setStatus(newStatus);
        return intervenantRepository.save(i);
    }

    public Intervenant getById(Long intervenantId) {
        return null;
    }
}
