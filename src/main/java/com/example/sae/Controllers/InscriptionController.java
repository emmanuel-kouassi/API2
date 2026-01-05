package com.example.sae.Controllers;

import com.example.sae.Models.Inscription;
import com.example.sae.Services.InscriptionService;
import com.example.sae.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @PostMapping
    public Inscription sInscrire(@RequestBody Inscription inscription) {
        return inscriptionService.inscrire(inscription);
    }

    @DeleteMapping("/annuler/{id}")
    public ResponseEntity<Void> annulerParId(@PathVariable("id") Long idInscription) {
        inscriptionRepository.deleteById(idInscription);
        return ResponseEntity.noContent().build();
    }
}