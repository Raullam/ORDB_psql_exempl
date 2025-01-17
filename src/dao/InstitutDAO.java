package dao;

import models.*;

import java.math.BigDecimal;
import java.util.List;

public interface InstitutDAO {
    // Declares the method to find a Cicle by its code
    Cicle findCicleByCodi(String codi);


    // METODO QUE RETORNA UNA ASIGNATURA
    Assignatura findAssignaturaByCodi(String codi);


    // Recupera tots els alumnes d'un cicle determinat.

    Alumne findAlumneByCicle(String nif);


    Alumne findAlumneContacteByNif(String nif);

    Matricula findMatriculaById(String nif, String codiAssginatura);


    List<Alumne> findAllAlumnes();

    // exercici 7
    List<Matricula>findAllMatriculaByNif(String nif);

    // Exercici 8

    // Recuperar totes les matrícules d'una assignatura.
    List<Matricula> findAllMatriculesByAssignatura(String codi_assignatura);

    // Exercici 9
    boolean canviarNota(String nif, String codiAssginatura, BigDecimal nota, int posicio);

// 10
    boolean addAlumne(Alumne alumne);

    // 11
    boolean addMatricula(String nif, String codiAssgnatura);

    boolean afegirNotes(String nif, String codiAssignatura, BigDecimal[] notes);


}
