package com.sunshineadvisory.finappapi.controllers;

import com.sunshineadvisory.finappapi.models.Don;
import com.sunshineadvisory.finappapi.models.Utilisateur;
import com.sunshineadvisory.finappapi.repositories.UtilisateurRepository;
import com.sunshineadvisory.finappapi.services.DonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dons")
public class DonController {
    @Autowired
    private DonService donService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping
    public Don creerDon(@RequestBody Map<String,String> body) {
        Utilisateur beneficiaire = utilisateurRepository.getReferenceById(Long.valueOf(body.get("beneficiaire")));
        Don don = new Don();
        don.setBeneficiaire(beneficiaire);
        don.setMontant(Double.valueOf(body.get("montant")));
        don.setDescription((body.get("description")));
        don.setDate(LocalDate.now());
        return donService.creerDon(don);
    }

    @GetMapping
    public List<Don> obtenirTousLesDons() {
        return donService.obtenirTousLesDons();
    }

    @GetMapping("/{id}")
    public Don obtenirDonParId(@PathVariable Long id) {
        return donService.obtenirDonParId(id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> mettreAJourDon(@PathVariable Long id, @RequestBody Map<String,String> body) {
        Map<String, Object> response = new HashMap<>();
        Utilisateur donateur = utilisateurRepository.getReferenceById(Long.valueOf(body.get("donateur")));
        Don don = donService.obtenirDonParId(id);
        Double montantCollecte = don.getMontantCollecte();
        if(montantCollecte >= don.getMontant()){
            don.setStatut(Don.Statut.TERMINE);
        }
        don.setDonateur(donateur);
        don.setMontantCollecte(montantCollecte+Double.valueOf(body.get("montant")));
        return donService.mettreAJourDon(don);
    }
}
