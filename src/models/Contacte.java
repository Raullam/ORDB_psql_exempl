package models;

import org.postgresql.util.PGobject;

import java.sql.SQLException;
import static util.EinesCadenes.parseValue;
import static util.EinesCadenes.tanca;


public class Contacte extends PGobject {
    private String telefon;
    private String correu;
    private String twitter;

    public Contacte(String telefon, String correu, String twitter) {
        this.telefon = telefon;
        this.correu = correu;
        this.twitter = twitter;
        this.setType("dades_contacte");
    }

    public Contacte() {
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "Contacte{" +
                "telefon='" + telefon + '\'' +
                ", correu='" + correu + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }

    @Override
    public void setValue(String value) throws SQLException {
        super.setValue(value);
        String[] atributs= parseValue(value);
        telefon = atributs[0];
        correu = atributs[1];
        twitter = atributs[2];
    }


    // hemos tenido que sobrescribir este metodo
    @Override
    public String getValue() {
        String resultat ="(";
        resultat += tanca(telefon) + ",";
        resultat += tanca(correu) + ",";
        resultat += tanca(twitter) + ")";
        return resultat;
    }
}
