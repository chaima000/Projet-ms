package tn.esprit.Foyer_ms.Foyer.dto;

public class Bloc {
    private String id;
    private String nom;
    private int nombreClasses;

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreClasses() {
        return nombreClasses;
    }

    public void setNombreClasses(int nombreClasses) {
        this.nombreClasses = nombreClasses;
    }
}
