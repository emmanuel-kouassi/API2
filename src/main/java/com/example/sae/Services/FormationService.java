package com.example.sae.Services;

import com.example.sae.Models.Formation;
import com.example.sae.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationService {
    @Autowired
    private FormationRepository formationRepository;

    public Formation saveFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    public List<Formation> listAll() {
        return formationRepository.findAll();
    }

    public Formation getById(Long id) {
        // .orElse(null) permet de retourner null si l'ID n'existe pas au lieu de faire planter l'appli
        return formationRepository.findById(id).orElse(null);
    }
}
