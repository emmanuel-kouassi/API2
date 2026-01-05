package com.example.sae.Services;

import com.example.sae.Models.*;
import com.example.sae.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private FormationRepository formationRepository;

    // Gestion des administrateurs
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


    // Modifier un utilisateur complet
    public User modifierUtilisateur(Long id, User details) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));

        // Champs hérités de Personne
        user.setNom(details.getNom());
        user.setPrenom(details.getPrenom());
        user.setLogin(details.getLogin());
        user.setPassword(details.getPassword());

        // Champs spécifiques à User
        user.setTelephone(details.getTelephone());
        user.setRole(details.getRole());
        user.setMail(details.getMail());
        user.setAdressePostale(details.getAdressePostale());
        user.setVilleRegion(details.getVilleRegion());

        user.setUserpp(details.getUserpp());

        return userRepository.save(user);
    }

    // Créer un UTILISATEUR
    public User creerUtilisateur(User user) {
        return userRepository.save(user); // Tu avais écrit "return user", donc ça n'enregistrait rien
    }

    //LISTE USER
    public List<User> listeUtilisateurs() {

        return userRepository.findAll();
    }

    // Supprimer un utilisateur
    public void supprimerUtilisateur(Long id) {
        userRepository.deleteById(id);
    }

    // Supprimer une SESSION
    public void supprimerSession(Long id) {
        sessionRepository.deleteById(id); // Il faut appeler le repository pour supprimer
    }
    // CREER SESSION
    public Session creerSession(Session session) {
        // Enregistre la session dans la base de données
        return sessionRepository.save(session);
    }

    // modifier une session
    public Session modifierSession(Long id, Session details) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session non trouvée"));

        session.setDateDebut(details.getDateDebut());
        session.setDateFin(details.getDateFin());
        session.setLieu(details.getLieu());
        session.setNbPlacesMax(details.getNbPlacesMax());
        session.setNbPlacesDispo(details.getNbPlacesDispo());
        session.setFormation(details.getFormation());
        session.setIntervenant(details.getIntervenant());

        return sessionRepository.save(session);
    }
    // LISTE SESSION
    public List<Session> listeSessions() {

        return sessionRepository.findAll();
    }

    // LISTE FORMATION
    public List<Formation> listeFormations() {
        return formationRepository.findAll();
    }
    // CREER FORMATION
    public Formation creerFormation(Formation formation) {
        return formationRepository.save(formation);
    }
   // MODIFIER UNE FOMATION
    public Formation modifierFormation(Long id, Formation details) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée avec l'id : " + id));

        formation.setTitre(details.getTitre());
        formation.setDescription(details.getDescription());
        formation.setCategorie(details.getCategorie());
        formation.setDureeJour(details.getDureeJour());
        formation.setPlaceMax(details.getPlaceMax());
        formation.setPlaceOccupe(details.getPlaceOccupe());

        return formationRepository.save(formation);
    }

    // SUPPRIMER FORMATION
    public void supprimerFormation(Long id) {
        formationRepository.deleteById(id);
    }

    // CREER INTERVENANT
    @Autowired
    private IntervenantRepository intervenantRepository;

    public Intervenant creerIntervenant(Intervenant intervenant) {
        return intervenantRepository.save(intervenant);
    }
    // SUPRIMER INTERVENANT
    public void supprimerIntervenant(Long id) {
        // On vérifie si l'intervenant existe avant de supprimer
        if (!intervenantRepository.existsById(id)) {
            throw new RuntimeException("Intervenant non trouvé avec l'id : " + id);
        }
        intervenantRepository.deleteById(id);
    }
   // VOIR TOUT LES INTERVENANT
    public List<Intervenant> listeIntervenants() {
        return intervenantRepository.findAll();
    }


}
