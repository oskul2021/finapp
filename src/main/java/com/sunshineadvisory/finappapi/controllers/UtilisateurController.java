package com.sunshineadvisory.finappapi.controllers;

import com.sunshineadvisory.finappapi.models.Utilisateur;
import com.sunshineadvisory.finappapi.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public Utilisateur enregistrerUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.enregistrerUtilisateur(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurService.obtenirTousLesUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur obtenirUtilisateurParId(@PathVariable Long id) {
        return utilisateurService.obtenirUtilisateurParId(id);
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> authentification(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("motdepasse");
        Map<String, Object> response = utilisateurService.authentifierUtilisateur(email,password);
        if ("Login success".equals(response.get("message"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
