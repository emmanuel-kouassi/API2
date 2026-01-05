package com.example.sae.Controllers;

import com.example.sae.Models.Intervenant;
import com.example.sae.Services.IntervenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/intervenants")
public class IntervenantController {
    @Autowired
    private IntervenantService intervenantService;
   // changer ses infos
    @PutMapping("/{id}")
    public ResponseEntity<Intervenant> updateIntervenant(@PathVariable Long id, @RequestBody Intervenant intervenantDetails) {
        return ResponseEntity.ok(intervenantService.update(id, intervenantDetails));
    }
   // changer son statue
    @PatchMapping("/{id}/status")
    public Intervenant changerStatut(@PathVariable Long id, @RequestParam String status) {
        return intervenantService.updateStatus(id, status);
    }

}
