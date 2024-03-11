package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.LibroDAO;
import com.example.bibliotecadigitale.Model.Libro;
import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibroDAOImpl implements LibroDAO {
    private final Connessione connessione = new Connessione();
    private final Connection conn = Connessione.getConnection();

    private ArrayList<String> rsToArrayList(ResultSet rs) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            array.add(rs.getString(i));
        }
        return array;
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> libroFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("numpagine") || tipoRicerca.equalsIgnoreCase("serie") || tipoRicerca.equalsIgnoreCase("presentazione")) {
            query = "SELECT * FROM libro WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            if (tipoRicerca.equalsIgnoreCase("datauscita")) {
                query = "SELECT * FROM libro WHERE " + tipoRicerca + " = '" + parolaChiave + "';";
            } else {
                query = "SELECT * FROM libro WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
            }
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> libro;
        while (rs.next()) {
            libro = rsToArrayList(rs);
            libroFinded.add(libro);
        }
        rs.close();
        return libroFinded;
    }

    @Override
    public ArrayList<String> get(String isbn) throws SQLException {
        ArrayList<String> libro = new ArrayList<>();
        String query = "SELECT * FROM libro WHERE isbn = '" + isbn + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            libro = rsToArrayList(rs);
        }
        rs.close();
        return libro;
    }

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> libroFinded = new ArrayList<>();

        String query = "SELECT * FROM libro;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> libro;
        while (rs.next()) {
            libro = rsToArrayList(rs);
            libroFinded.add(libro);
        }
        rs.close();
        return libroFinded;
    }

    @Override
    public void insert(ArrayList<String> libro) throws SQLException, IllegalArgumentException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO libro VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

        //Inserimento valori non nullable
        ps.setString(1, libro.get(0));//isbn
        ps.setString(2, libro.get(1));//titolo
        ps.setInt(4, Integer.parseInt(libro.get(3)));//numpagine
        ps.setString(5, libro.get(4));//tipo
        ps.setString(7, libro.get(6));//descrizione
        ps.setString(8, libro.get(7));//fruizione
        ps.setString(9, libro.get(8));//editore
        ps.setString(10, libro.get(9));//autore
        ps.setString(12, libro.get(11));//lingua

        //Inserimento valori nullable
        if (libro.get(2) == null || libro.get(2).isEmpty()) {//genere
            ps.setNull(3, java.sql.Types.VARCHAR);
        } else {
            ps.setString(3, libro.get(2));
        }
        if (libro.get(5) == null || libro.get(5).isEmpty()) {//materia
            ps.setNull(6, java.sql.Types.VARCHAR);
        } else {
            ps.setString(6, libro.get(5));
        }
        if (libro.get(10) == null || libro.get(10).isEmpty()) {//datauscita
            ps.setNull(11, Types.DATE);
        } else {
            ps.setString(11, libro.get(10));
        }
        if (libro.get(12) == null || libro.get(12).isEmpty()) {//successivo
            ps.setNull(13, java.sql.Types.VARCHAR);
        } else {
            ps.setString(13, libro.get(12));
        }
        if (libro.get(13) == null || libro.get(13).isEmpty()) {//serie
            ps.setNull(14, java.sql.Types.INTEGER);
        } else {
            ps.setInt(14, Integer.parseInt(libro.get(13)));
        }
        if (libro.get(14) == null || libro.get(14).isEmpty()) {//presentazione
            ps.setNull(15, java.sql.Types.INTEGER);
        } else {
            ps.setInt(15, Integer.parseInt(libro.get(14)));
        }
        ps.executeUpdate();
        ps.close();
    }


    @FXML
    public void delete(ArrayList<String> libro) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM libro WHERE ISBN = ?;");
        ps.setString(1, libro.get(0));
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> libro) throws SQLException, IllegalArgumentException {
        PreparedStatement ps = conn.prepareStatement("UPDATE libro SET titolo = ?, genere = ?, numpagine = ?, tipo = ?, materia = ?, descrizione = ?, fruizione = ?, editore = ?, autore = ?, datauscita = ?, lingua = ?, successivo = ?, serie = ?, presentazione = ? WHERE isbn = ?;");
        //Inserimento valori non nullable
        ps.setString(15, libro.get(0));//isbn insert
        ps.setString(1, libro.get(1));//titolo
        ps.setString(2, libro.get(2));//genere
        ps.setInt(3, Integer.parseInt(libro.get(3)));//numpagine
        ps.setString(4, libro.get(4));//tipo
        if (libro.get(6) != null) {
            ps.setString(6, libro.get(6).replace("'", "''"));//descrizione
        }
        ps.setString(7, libro.get(7));//fruizione
        ps.setString(8, libro.get(8));//editore
        ps.setString(9, libro.get(9));//autore
        ps.setString(11, libro.get(11));//lingua

        //Inserimento valori nullable
        if (libro.get(5) == null) {//materia
            ps.setNull(5, java.sql.Types.VARCHAR);
        } else {
            ps.setString(5, libro.get(5));
        }
        if (libro.get(10) == null) {//datauscita
            ps.setNull(10, java.sql.Types.VARCHAR);
        } else {
            ps.setDate(10, Date.valueOf(libro.get(10)));
        }
        if (libro.get(12) == null) {//successivo
            ps.setNull(12, java.sql.Types.VARCHAR);
        } else {
            ps.setString(12, libro.get(12));
        }
        if (libro.get(13).equals("null")) {//serie
            ps.setNull(13, java.sql.Types.INTEGER);
        } else {
            ps.setInt(13, Integer.parseInt(libro.get(13)));
        }
        if (libro.get(14).equals("null")) {//presentazione
            ps.setNull(14, java.sql.Types.INTEGER);
        } else {
            ps.setInt(14, Integer.parseInt(libro.get(14)));
        }
        ps.executeUpdate();
        ps.close();
    }

//    private boolean check(ArrayList<String> libro) throws SQLException {
//        String query = "SELECT * FROM libro WHERE isbn = '" + isbn + "';";
//        ResultSet rs = connessione.executeSearch(query);
//        return rs.next();
//    }
}
