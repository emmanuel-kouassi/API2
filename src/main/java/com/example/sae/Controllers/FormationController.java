package com.example.sae.Controllers;

import com.example.sae.Models.Formation;
import com.example.sae.Services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/formations")
public class FormationController {
    @Autowired
    private FormationService formationService;

    @GetMapping
    public List<Formation> getAll() {
        return formationService.listAll();
    }

    @GetMapping("/{id}")
    public Formation getOne(@PathVariable Long id) {
        return formationService.getById(id);
    }

    @PostMapping
    public Formation create(@RequestBody Formation formation) {
        return formationService.saveFormation(formation);
    }
}