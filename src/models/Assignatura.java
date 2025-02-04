package models;

public class Assignatura {
    private String codi;
    private String nom;
    private Cicle cicle;

    public Assignatura(String codi, String nom, Cicle cicle) {
        this.codi = codi;
        this.nom = nom;
        this.cicle = cicle;
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

    public Cicle getCicle() {
        return cicle;
    }

    public void setCicle(Cicle cicle) {
        this.cicle = cicle;
    }

    @Override
    public String toString() {
        return "Assignatura{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                ", cicle=" + cicle +
                '}';
    }
}
