package dao;

import models.*;
import org.postgresql.PGConnection;
import util.BaseDades;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstitutDAOimpl implements InstitutDAO {

    @Override
    public Cicle findCicleByCodi(String codi) {
        String query = "SELECT * FROM cicles WHERE codi = ?";
        try (Connection connection = BaseDades.getConnection();
             PreparedStatement pre = connection.prepareStatement(query)) {

            pre.setString(1, codi);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return new Cicle(codi, rs.getString("nom"));
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Assignatura findAssignaturaByCodi(String codi) {
        String query = "SELECT nom, codi_cicle FROM assignatures WHERE codi = ?";
        try (Connection connection = BaseDades.getConnection();
             PreparedStatement pre = connection.prepareStatement(query)) {

            // Set the parameter value for the prepared statement
            pre.setString(1, codi);

            // Execute the query and process the result set
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    // Use rs.getString("cicle") to find and return the associated Assignatura
                    return new Assignatura(codi,rs.getString("nom"),findCicleByCodi(rs.getString("codi_cicle")));
                }
            }

        } catch (SQLException e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
        }

        // Return null if no result is found or an exception occurs
        return null;
    }

    @Override
    public Alumne findAlumneByCicle(String nif) {
        String query = "SELECT nom, llinatges FROM alumnes WHERE nif = ?";
        try (Connection connection = BaseDades.getConnection();
             PreparedStatement pre = connection.prepareStatement(query)) {

            // Set the NIF parameter for the query
            pre.setString(1, nif);

            // Execute the query and process the result set
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    // Create and return an Alumne object with retrieved data
                    return new Alumne(nif, rs.getString("nom"), rs.getString("llinatges"),null);
                }
            }

        } catch (SQLException e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
        }

        // Return null if no result is found or an exception occurs
        return null;
    }

    // Exercici

    @Override
    public Alumne findAlumneContacteByNif(String nif) {
        String query = "SELECT nom, llinatges, contacte FROM alumnes WHERE nif = ?";
        try (Connection connection = BaseDades.getConnection();
             PreparedStatement pre = connection.prepareStatement(query)) {
            // Set the NIF parameter for the query
            pre.setString(1, nif);

            PGConnection pgConnection = (PGConnection) connection;
            pgConnection.addDataType("dades_contacte", Contacte.class);

            // Execute the query and process the result set
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Contacte contacte = (Contacte) rs.getObject("contacte");
                    // Create and return an Alumne object with retrieved data
                    return new Alumne(nif, rs.getString("nom"), rs.getString("llinatges"),contacte);
                }
            }

        } catch (SQLException e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
        }

        // Return null if no result is found or an exception occurs
        return null;
    }

    //


    @Override
    public Matricula findMatriculaById(String nif, String codiAssginatura) {
        try (Connection connection = BaseDades.getConnection();
        PreparedStatement pre = connection.prepareStatement("select notes from matricules where nif_alumne = ? and codi_assignatura = ?")){
            pre.setString(1,nif);
            pre.setString(2,codiAssginatura);

            try (ResultSet rs = pre.executeQuery()) {
                if(rs.next()){
                    Array ref = rs.getArray("notes");
                    BigDecimal[] notes =(BigDecimal[]) ref.getArray();
                    return new Matricula(findAlumneContacteByNif(nif), findAssignaturaByCodi(codiAssginatura), notes);
                }else
                    return null;

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Alumne> findAllAlumnes() {
        List<Alumne> alumnes = new ArrayList<>(); // Lista para almacenar los alumnos
        try (Connection connection = BaseDades.getConnection();
             PreparedStatement pre = connection.prepareStatement("select nif, nom, llinatges, contacte from alumnes")) {

            // Registrar el tipo de datos personalizado en la conexión PostgreSQL
            PGConnection pgConnection = (PGConnection) connection;
            pgConnection.addDataType("dades_contacte", Contacte.class);

            // Ejecutar la consulta y procesar los resultados
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) { // Cambiar if por while para procesar todos los registros
                    Contacte contacte = (Contacte) rs.getObject("contacte");
                    Alumne alumne = new Alumne(
                            rs.getString("nif"),
                            rs.getString("nom"),
                            rs.getString("llinatges"),
                            contacte
                    );
                    alumnes.add(alumne); // Agregar el alumno a la lista
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones (podría mejorarse con un logger)
        }

        return alumnes; // Devolver la lista de alumnos
    }


    // Exercici 7


    @Override
    public List<Matricula> findAllMatriculaByNif(String nif) {
        List<Matricula> matriculas = new ArrayList<>(); // Lista para almacenar las matrículas
        try (Connection connection = BaseDades.getConnection();
             PreparedStatement pre = connection.prepareStatement(
                     "select codi_assignatura, notes from matricules where nif_alumne = ?")) {

            pre.setString(1, nif); // Establecer el parámetro de la consulta

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    // Obtener el array de notas
                    Array ref = rs.getArray("notes");
                    BigDecimal[] notes = (BigDecimal[]) ref.getArray();

                    // Crear la matrícula con los datos obtenidos
                    Matricula matricula = new Matricula(
                            findAlumneContacteByNif(nif),                         // Obtener el alumno
                            findAssignaturaByCodi(rs.getString("codi_assignatura")), // Obtener la asignatura
                            notes                                                // Notas
                    );

                    matriculas.add(matricula); // Agregar la matrícula a la lista
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de excepciones (puede mejorarse con un logger)
        }

        return matriculas; // Devolver la lista de matrículas (vacía si no hay resultados)
    }

    @Override
    public List<Matricula> findAllMatriculesByAssignatura(String codi_assignatura) {
        List<Matricula> matriculas = new ArrayList<>(); // Lista para almacenar las matrículas
        try (Connection connection = BaseDades.getConnection();
             PreparedStatement pre = connection.prepareStatement(
                     "select nif_alumne, notes from matricules where codi_assignatura = ?")) {

            pre.setString(1, codi_assignatura); // Establecer el parámetro de la consulta

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    // Obtener el array de notas
                    Array ref = rs.getArray("notes");
                    BigDecimal[] notes;
                    // Comprovar si 'ref' és nul abans d'intentar accedir-hi
                    if (ref == null) {
                        notes = new BigDecimal[0]; // Obtenir l'array de notes si no és nul
                    }else {
                        notes = (BigDecimal[]) ref.getArray();

                    }

                    // Crear la matrícula con los datos obtenidos
                    Matricula matricula = new Matricula(
                            findAlumneContacteByNif(rs.getString("nif_alumne")),                         // Obtener el alumno
                            findAssignaturaByCodi(codi_assignatura), // Obtener la asignatura
                            notes                                                // Notas
                    );

                    matriculas.add(matricula); // Agregar la matrícula a la lista
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de excepciones (puede mejorarse con un logger)
        }

        return matriculas; // Devolver la lista de matrículas (vacía si no hay resultados)
    }


    @Override
    public boolean canviarNota(String nif, String codiAssginatura, BigDecimal nota, int posicio) {
        try (Connection connection = BaseDades.getConnection();
        PreparedStatement pre = connection.prepareStatement("update matricules set notes[?] = ? where nif_alumne = ? and codi_assignatura = ?")){
            pre.setInt(1,posicio);
            pre.setBigDecimal(2, nota);
            pre.setString(3,nif);
            pre.setString(4, codiAssginatura);

            int filesAfectades = pre.executeUpdate();
            return (filesAfectades == 1);



        }catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAlumne(Alumne alumne) {
        try(Connection connection = BaseDades.getConnection();
        PreparedStatement pre = connection.prepareStatement("insert into alumnes values (?,?,?,?)")){
            pre.setString(1,alumne.getNif());
            pre.setString(2,alumne.getNom());
            pre.setString(3, alumne.getLlinatges());
            pre.setObject(4,alumne.getContacte());
            int filesAfectades = pre.executeUpdate();
            return (filesAfectades == 1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addMatricula(String nif, String codiAssgnatura) {
        try (Connection connection = BaseDades.getConnection();
        PreparedStatement pre = connection.prepareStatement("insert into matricules values(?,?,null)")){
            pre.setString(1,nif);
            pre.setString(2,codiAssgnatura);
            int filesAfectades = pre.executeUpdate();
            return (filesAfectades == 1);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean afegirNotes(String nif, String codiAssignatura, BigDecimal[] notes) {
        try (Connection connection = BaseDades.getConnection();
        PreparedStatement pre = connection.prepareStatement("update matricules set notes = ? where nif_alumne = ? and codi_assignatura =?")){
           Array arrayNotes=  connection.createArrayOf("NUMERIC",notes);
            pre.setArray(1,arrayNotes);
            pre.setString(2,nif);
            pre.setString(3,codiAssignatura);
            int filesAfectades = pre.executeUpdate();
            return (filesAfectades == 1);

        }catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    }
}
