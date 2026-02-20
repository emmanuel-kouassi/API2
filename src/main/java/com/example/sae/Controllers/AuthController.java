package com.example.sae.Controllers;

import com.example.sae.Models.User;
import com.example.sae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:5173,https://swipe2.vercel.app/") // Autorise ton frontend Vite
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User loginData) {
        // 1. Chercher l'utilisateur par son login
        Optional<User> user = userRepository.findByLogin(loginData.getLogin());

        // 2. Vérification simple du mot de passe (en texte clair pour tes tests)
        if (user.isPresent() && user.get().getPassword().equals(loginData.getPassword())) {
            // Succès : on renvoie l'utilisateur (qui contient son rôle "apprenant")
            return ResponseEntity.ok(user.get());
        } else {
            // Échec : on renvoie une erreur 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
        }
    }
}