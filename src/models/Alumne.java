package models;

public class Alumne {
    private String nif;
    private String nom;
    private String llinatges;
    private Contacte contacte;

    public Alumne(String nif, String nom, String llinatges, Contacte contacte) {
        this.nif = nif;
        this.nom = nom;
        this.llinatges = llinatges;
        this.contacte = contacte;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatges() {
        return llinatges;
    }

    public void setLlinatges(String llinatges) {
        this.llinatges = llinatges;
    }

    public Contacte getContacte() {
        return contacte;
    }

    public void setContacte(Contacte contacte) {
        this.contacte = contacte;
    }

    @Override
    public String toString() {
        return "Alumne{" +
                "nif='" + nif + '\'' +
                ", nom='" + nom + '\'' +
                ", llinatges='" + llinatges + '\'' +
                ", contacte=" + contacte +
                '}';
    }
}
