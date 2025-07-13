package tn.esprit.Foyer_ms.Foyer.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Foyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    // One foyer may contain multiple blocs, represented here as IDs
    @ElementCollection
    private List<String> blocIds;


    // Constructors
    public Foyer() {}

    public Foyer(String nom, List<String> blocIds) {
        this.nom = nom;
        this.blocIds = blocIds;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getBlocIds() {
        return blocIds;
    }

    public void setBlocIds(List<String> blocIds) {
        this.blocIds = blocIds;
    }
}

