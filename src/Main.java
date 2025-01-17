import dao.InstitutDAO;
import dao.InstitutDAOimpl; // Import your implementation
import models.*;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    // Define the database URL as a constant
    private static final String URL = "your_database_url_here";

    public static void main(String[] args) {
        InstitutDAO dao = new InstitutDAOimpl();


        System.out.println("\nExercici 1");

        Cicle cicle = dao.findCicleByCodi("CIC01");

        if (cicle != null) {
            System.out.println(cicle);
        } else {
            System.out.println("Cicle not found.");
        }

        // exercici 2
        System.out.println("\nExercici 2");

        Assignatura as = dao.findAssignaturaByCodi("INF001");
        System.out.println(as);

        // exercici 3
        System.out.println("\nExercici 3");

        Alumne al = dao.findAlumneByCicle("23456789B");
        System.out.println(al);

        // Exercici 4
        System.out.println("\nExercici 4");
        // Recupera totes les notes d'un alumne d'una assignatura determinada.

        // añadir extends en contacte PGob
        //añadir constructor sin parametros

        Alumne al2 = dao.findAlumneContacteByNif("23456789B");
        System.out.println(al2);

        // Exercici 5
        System.out.println("\nExercici 5");

        Matricula matricula = dao.findMatriculaById("12345678A", "INF001");
        System.out.println(matricula);


        // Exercici 6 mostra tots els contactes
        System.out.println("\nExercici 6");

        List<Alumne> llistaAlumnes = dao.findAllAlumnes();
        for (Alumne alumne : llistaAlumnes) {
            System.out.println(alumne);
        }

        // Exercici 7 Cerca un alumne per el seu correu.
        System.out.println("\nExercici 7");

        List<Matricula> matriculas = dao.findAllMatriculaByNif("12345678A");
        for(Matricula m : matriculas){
            System.out.println(m);
        }

        // Exercici 8

        System.out.println("\nExercici 8");
        List<Matricula> matriculas2 = dao.findAllMatriculesByAssignatura("INF001");
        for(Matricula m : matriculas2){
            System.out.println(m);
        }

        // Exercici 9
        System.out.println("\nExercici 9");

        boolean resultat = dao.canviarNota("12345678A","INF001", BigDecimal.valueOf(9.9),1);
        System.out.println(resultat);


        // Exercici 10
        System.out.println("\nExercici 10");

        Alumne alumnee = new Alumne("43227709J", "Raul", "Lama",new Contacte("698600080","raullama@paucasesnovescifp.cat","Raullam") );
        boolean resultatt = dao.addAlumne(alumnee);
        System.out.println(resultatt);

        // Exercici 11
        System.out.println("\nExercici 11");

        // MATRICULAR UN ALUMNE A UNA ASIGNATURA
        boolean newMatricula = dao.addMatricula("43227708J","INF001");
        System.out.println(newMatricula);


        // Exercici 12
        System.out.println("\nExercici 12");

        BigDecimal[] notes = new BigDecimal[]{BigDecimal.valueOf(7.6),BigDecimal.valueOf(9.8),BigDecimal.valueOf(7.6)};
        boolean resultattt= dao.afegirNotes("43227708J","INF001",notes);
        System.out.println(resultattt);

    }

    // Afegir notes a un Alumne






}
