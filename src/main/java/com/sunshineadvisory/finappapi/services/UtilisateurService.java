package com.sunshineadvisory.finappapi.services;

import com.sunshineadvisory.finappapi.configs.JwtUtil;
import com.sunshineadvisory.finappapi.models.Utilisateur;
import com.sunshineadvisory.finappapi.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Transactional
    public Utilisateur enregistrerUtilisateur(Utilisateur utilisateur) {
        if(utilisateurRepository.existsByEmail(utilisateur.getEmail())){
            throw new IllegalArgumentException("l'adresse email est déjà utilisée");
        }
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public Map<String, Object> authentifierUtilisateur(String email, String motDePasse){
        Map<String, Object> response = new HashMap<>();

        // Rechercher l'utilisateur par email
        Optional<Utilisateur> userOptional = utilisateurRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Utilisateur utilisateur = userOptional.get();

            // Vérification du mot de passe
            if (passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
                // Générer le token JWT pour l'utilisateur
                String token = jwtUtil.generateToken(email);

                // Construire la réponse avec le token JWT, l'utilisateur et ses comptes
                response.put("message", "Login success");
                response.put("token", token); // Ajouter le token JWT dans la réponse
            } else {
                response.put("message", "Login failed: Invalid credentials");
            }
        } else {
            response.put("message", "Login failed: User not found");
        }
        return response;
    }

    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur obtenirUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Optional<Utilisateur> obtenirUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
