package com.example.sae.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    // LA RELATION MANQUANTE EST ICI
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructeurs
    public Session() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}