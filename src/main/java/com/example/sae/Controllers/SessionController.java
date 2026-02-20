package com.example.sae.Controllers;

import com.example.sae.Services.SessionService;
import com.example.sae.Models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:5173,https://swipe2.vercel.app/")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping
    public Session planifier(@RequestBody Session session) {
        Session Session = null;
        return sessionService.planifierSession(Session);
    }

    @GetMapping("/formation/{id}")
    public List<Session> getByFormation(@PathVariable Long id) {
        return sessionService.getSessionsByFormation(id);
    }
}
