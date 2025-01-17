package models;

import java.util.Objects;

public class Cicle {
    private String codi;
    private String nom;

    public Cicle(String codi, String nom) {
        this.codi = codi;
        this.nom = nom;
    }

    public Cicle() {
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Cicle{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
