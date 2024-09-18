package com.sunshineadvisory.finappapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Utilisateur donateur;

    @ManyToOne(optional = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Utilisateur beneficiaire;

    private Double montant;
    private String description;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE;
    private Double montantCollecte = 0.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum Statut {
        EN_ATTENTE, TERMINE, ANNULE
    }

    public Utilisateur getDonateur() {
        return donateur;
    }

    public void setDonateur(Utilisateur donateur) {
        this.donateur = donateur;
    }

    public Utilisateur getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(Utilisateur beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Double getMontantCollecte() {
        return montantCollecte;
    }

    public void setMontantCollecte(Double montantCollecte) {
        this.montantCollecte = montantCollecte;
    }
}
