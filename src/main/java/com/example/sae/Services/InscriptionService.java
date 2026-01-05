package com.example.sae.Services;

import com.example.sae.Models.Inscription;
import com.example.sae.Models.User;
import com.example.sae.repository.InscriptionRepository;
import com.example.sae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public Inscription inscrire(Inscription inscription) {
        // 1. Créer l'utilisateur
        User newUser = new User();
        newUser.setLogin(inscription.getLogin());
        newUser.setPassword(inscription.getPassword());
        newUser.setRole("apprenant"); // Attribution automatique

        // 2. Transfert des nouveaux champs vers User
        newUser.setNom(inscription.getNom());
        newUser.setPrenom(inscription.getPrenom());
        newUser.setMail(inscription.getMail());
        newUser.setTelephone(inscription.getTelephone());
        newUser.setAdressePostale(inscription.getAdressePostale()); // Plus d'underscore ici !
        newUser.setVilleRegion(inscription.getVilleRegion());

        // 3. Sauvegarder User d'abord
        User savedUser = userRepository.save(newUser);

        // 4. Lier et dater l'inscription
        inscription.setUser(savedUser);
        inscription.setDateInscription(LocalDate.now());

        return inscriptionRepository.save(inscription);
    }}