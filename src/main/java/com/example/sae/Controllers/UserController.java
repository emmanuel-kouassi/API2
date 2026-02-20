package com.example.sae.Controllers;

import com.example.sae.Models.User;
import com.example.sae.Models.Personne;
import com.example.sae.Services.UserService;
import com.example.sae.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173,https://swipe2.vercel.app/")
public class UserController { // Début de la classe

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String mail = loginData.get("mail");
        String password = loginData.get("password");

        return personneRepository.findByMail(mail)
                .map(personne -> {
                    if (personne.getPassword().equals(password)) {
                        return ResponseEntity.ok(personne);
                    } else {
                        return ResponseEntity.status(401).body("Mot de passe incorrect");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Utilisateur non trouvé"));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.update(id, userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
}