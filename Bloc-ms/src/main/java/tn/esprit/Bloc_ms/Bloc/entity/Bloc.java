package tn.esprit.Bloc_ms.Bloc.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blocs")
public class Bloc {

    @Id
    private String id;

    private String nom;
    private int nombreClasses;

    public Bloc() {}

    public Bloc(String id, String nom, int nombreClasses) {
        this.id = id;
        this.nom = nom;
        this.nombreClasses = nombreClasses;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {  // 🔹 Ajoute ce setter si manquant
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