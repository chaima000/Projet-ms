package tn.esprit.Foyer_ms.Foyer.dto;

import java.util.List;

public class FoyerWithBlocsRequest {
    private String nom;
    private List<String> blocIds;

    // Getters et Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public List<String> getBlocIds() { return blocIds; }
    public void setBlocIds(List<String> blocIds) { this.blocIds = blocIds; }
}
