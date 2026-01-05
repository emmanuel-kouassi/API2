package com.example.sae.repository;

import com.example.sae.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Cette méthode permet de retrouver un User par son mail pour le login
    // Spring Data JPA générera automatiquement la requête SQL car le champ "mail" existe dans User.java
    Optional<User> findByMail(String mail);

    // Vous pouvez garder celle-ci si vous voulez aussi permettre la connexion par identifiant (login)
    Optional<User> findByLogin(String login);
}