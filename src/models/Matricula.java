package models;

import java.math.BigDecimal;
import java.util.Arrays;

public class Matricula {
    private Alumne nif_alumne;
    private Assignatura codi_assignatura;
    private BigDecimal[] notes;

    public Matricula(Alumne nif_alumne, Assignatura codi_assignatura, BigDecimal[] notes) {
        this.nif_alumne = nif_alumne;
        this.codi_assignatura = codi_assignatura;
        this.notes = notes;
    }

    public Matricula() {
    }

    public Alumne getNif_alumne() {
        return nif_alumne;
    }

    public void setNif_alumne(Alumne nif_alumne) {
        this.nif_alumne = nif_alumne;
    }

    public Assignatura getCodi_assignatura() {
        return codi_assignatura;
    }

    public void setCodi_assignatura(Assignatura codi_assignatura) {
        this.codi_assignatura = codi_assignatura;
    }

    public BigDecimal[] getNotes() {
        return notes;
    }

    public void setNotes(BigDecimal[] notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "nif_alumne=" + nif_alumne +
                ", codi_assignatura=" + codi_assignatura +
                ", notes=" + Arrays.toString(notes) +
                '}';
    }
}
