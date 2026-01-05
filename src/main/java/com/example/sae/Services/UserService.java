package com.example.sae.Services;

import com.example.sae.Models.User;
import com.example.sae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
   // VOIR TOUTE SES INFOS
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
  // CHNGER SES INFOS
    public User update(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Mise à jour des champs autorisés
        user.setNom(userDetails.getNom());
        user.setPrenom(userDetails.getPrenom());
        user.setMail(userDetails.getMail());
        user.setTelephone(userDetails.getTelephone());
        user.setAdressePostale(userDetails.getAdressePostale());
        user.setVilleRegion(userDetails.getVilleRegion());

        // Si l'utilisateur change sa photo de profil
        if (userDetails.getUserpp() != null) {
            user.setUserpp(userDetails.getUserpp());
        }

        return userRepository.save(user);
    }
}