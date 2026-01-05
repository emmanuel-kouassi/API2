package com.example.sae.Controllers;

import com.example.sae.Models.Inscription;
import com.example.sae.Services.AttestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attestations")
@CrossOrigin(origins = "http://localhost:5173")
public class AttestationController {
    @Autowired
    private AttestationService attestationService;

   // @PostMapping("/generer")
   // public Inscription.Attestation generer(@RequestBody Inscription.Attestation attestation) {
       // return attestationService.genererAttestation(attestation);
   // }
}
