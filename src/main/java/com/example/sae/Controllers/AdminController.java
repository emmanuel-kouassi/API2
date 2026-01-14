package com.example.sae.Controllers;

import com.example.sae.Models.*;
import com.example.sae.Services.AdminService;
import com.example.sae.repository.IntervenantRepository;
import com.example.sae.repository.PaiementRepository;
import com.example.sae.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaiementRepository paiementRepository;
    @Setter
    @Autowired
    private com.example.sae.repository.IntervenantRepository intervenantRepository;

    // Créer un utilisateur
    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        // Vérifie si c'est bien "creerUtilisateur" dans ton AdminService
        return adminService.creerUtilisateur(user);
    }

    // Récupérer tous les utilisateurs
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.listeUtilisateurs();
    }

    // Modifier un utilisateur
    @PutMapping("/users/{id}")
    public User updateUtilisateur(@PathVariable Long id, @RequestBody User user) {
        return adminService.modifierUtilisateur(id, user);
    }
   // Supprimer un user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        adminService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }


    // creer un intervant
    @PostMapping("/intervenants")
    public Intervenant saveIntervenant(@RequestBody Intervenant intervenant) {
        return adminService.creerIntervenant(intervenant);
    }

    // supprimer un intervenant
    @DeleteMapping("/intervenants/{id}")
    public ResponseEntity<Void> supprimerIntervenant(@PathVariable Long id) {
        adminService.supprimerIntervenant(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer tous les intervenants
    @GetMapping("/intervenants")
    public List<com.example.sae.Models.Intervenant> getAllIntervenants() {
        return intervenantRepository.findAll();
    }

    // Creer une formation
    @PostMapping("/formations")
    public Formation saveFormation(@RequestBody Formation formation) {
        return adminService.creerFormation(formation);
    }

    // modifier une formation
    @PutMapping("/formations/{id}")
    public Formation updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
        return adminService.modifierFormation(id, formation);
    }

    // Supprimer une formation
    @DeleteMapping("/formations/{id}")
    public ResponseEntity<Void> supprimerFormation(@PathVariable Long id) {
        adminService.supprimerFormation(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer toutes les formations
    @GetMapping("/formations")
    public List<Formation> getAllFormations() {
        return adminService.listeFormations();
    }

    // creer une seesion
    @PostMapping("/sessions")
    public Session saveSession(@RequestBody Session session) {
        // Note : On retourne généralement l'objet Session créé
        return adminService.creerSession(session);
    }

    // Récupérer toutes les sessions
    @GetMapping("/sessions")
    public List<Session> getAllSessions() {
        return adminService.listeSessions();
    }

    // modifier une session
    @PutMapping("/sessions/{id}")
    public Session updateSession(@PathVariable Long id, @RequestBody Session sessionDetails) {
        return adminService.modifierSession(id, sessionDetails);
    }
    // Supprimer une session
    @DeleteMapping("/sessions/{id}")
    public ResponseEntity<Void> supprimerSession(@PathVariable Long id) {
        // Vérifie si c'est bien "supprimerSession" dans ton AdminService
        adminService.supprimerSession(id);
        return ResponseEntity.noContent().build();
    }
    // voir tout les paiements
    @GetMapping("/paiements")
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPaiementsByUser(@PathVariable Long userId) {
        try {
            // Cette méthode doit être ajoutée dans PaiementRepository.java d'abord
            return ResponseEntity.ok(paiementRepository.findByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur : " + e.getMessage());
        }
    }


    // Supprimer un paiement par son ID
    @DeleteMapping("/paiements/{id}")
    public ResponseEntity<Void> supprimerPaiement(@PathVariable Long id) {
        paiementRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public IntervenantRepository getIntervenantRepository() {
        return intervenantRepository;
    }

}