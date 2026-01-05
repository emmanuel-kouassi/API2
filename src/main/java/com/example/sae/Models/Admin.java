package com.example.sae.Models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data @EqualsAndHashCode(callSuper = true)
public class Admin extends Personne {

    @Override
    public String getRole() {
        return "ADMIN";
    }
}