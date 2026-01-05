package com.example.sae.Services;

import com.example.sae.Models.*;
import com.example.sae.repository.AttestationRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class AttestationService {

    @Autowired private JavaMailSender mailSender;
    @Autowired private AttestationRepository attestationRepository;

    // Nom de méthode aligné avec le Controller
    public <Intervenant> void validerEtEnvoyerAttestation(User user, Formation formation, Intervenant intervenant, Double note) throws Exception {

        // 1. Correction du symbole : on utilise Attestation directement
        Attestation attestation = new Attestation();
        attestation.setUser(user);
        attestation.setFormation(formation);
        //attestation.setIntervenant(intervenant);
        attestation.setNote(note);
        //attestationRepository.save(attestation);

        // 2. Utilisation de MimeMessage pour le PDF
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getMail());

        if (note >= 10) {
            helper.setSubject("Félicitations : Votre Attestation");
            helper.setText("Bonjour " + user.getPrenom() + ", votre attestation est jointe.");

            // 3. Génération PDF
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("ATTESTATION DE REUSSITE"));
            document.add(new Paragraph("Eleve: " + user.getNom() + " " + user.getPrenom()));
            document.add(new Paragraph("Note: " + note + "/20"));
           // document.add(new Paragraph("Intervenant: " + intervenant.getNom()));
            document.close();

            helper.addAttachment("Attestation.pdf", new ByteArrayResource(out.toByteArray()));
        } else {
            helper.setSubject("Résultats formation");
            helper.setText("Note insuffisante : " + note + "/20");
        }

        mailSender.send(message);
    }
}