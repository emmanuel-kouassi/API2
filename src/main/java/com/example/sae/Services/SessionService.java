package com.example.sae.Services;

import com.example.sae.repository.SessionRepository;
import com.example.sae.Models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session planifierSession(Session session) {
        return (Session) sessionRepository.save(session);
    }

    public List<com.example.sae.Models.Session> getSessionsByFormation(Long formationId) {
        return sessionRepository.findByFormation_IdFormation(formationId);
    }

    public Session getById(Long sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }
}
