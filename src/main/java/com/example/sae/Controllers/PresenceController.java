package com.example.sae.Controllers;

import com.example.sae.Models.SuiviPresence;
import com.example.sae.Services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presences")
@CrossOrigin(origins = "http://localhost:5173,https://swipe2.vercel.app/")
public class PresenceController {
    @Autowired
    private PresenceService presenceService;

    @PostMapping("/valider")
    public SuiviPresence valider(@RequestBody SuiviPresence presence) {
        return presenceService.validerPresence(presence);
    }

    @GetMapping("/user/{userId}")
    public List<SuiviPresence> getHistorique(@PathVariable Long userId) {
        return presenceService.HistoriqueParUtilisateur(userId);
    }
}
