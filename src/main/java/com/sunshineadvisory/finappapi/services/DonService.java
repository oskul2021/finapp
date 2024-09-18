package com.sunshineadvisory.finappapi.services;

import com.sunshineadvisory.finappapi.models.Don;
import com.sunshineadvisory.finappapi.models.Utilisateur;
import com.sunshineadvisory.finappapi.repositories.DonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DonService {
    private final DonRepository donRepository;

    public DonService(DonRepository donRepository) {
        this.donRepository = donRepository;
    }

    public Don creerDon(Don don) {
        return donRepository.save(don);
    }

    public List<Don> obtenirTousLesDons() {
        return donRepository.findAll();
    }

    public Don obtenirDonParId(Long id) {
        return donRepository.findById(id).orElse(null);
    }

    public Map<String, Object> mettreAJourDon(Don don) {
        Map<String, Object> response = new HashMap<>();
        response.put("message","Ce don est terminé");
        if(don.getStatut().equals("TERMINE")){
            return response;
        }
        donRepository.save(don);
        response.put("message","Votre Don a été enregistré");
        return response;
    }
}
