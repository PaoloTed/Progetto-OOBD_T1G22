package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileLDAO;
import com.example.bibliotecadigitale.Model.DisponibileL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileLDAOImpl implements DisponibileLDAO {
    private final Connessione connessione = new Connessione();

    @Override
    public DisponibileL get(int coda, String isbn) throws SQLException {
        DisponibileL disponibileL;
        try {
            String query = "SELECT * FROM disponibile_l WHERE coda = '" + coda + "'AND isbn = '" + isbn + "';";
            disponibileL = new DisponibileL();
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                disponibileL.setCoda(rs.getInt(1));
                disponibileL.setIsbn(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileL;
    }


    public ArrayList<DisponibileL> getAcquisti(String isbn) throws SQLException {
        ArrayList<DisponibileL> disponibileL;
        try {
            String query = "SELECT * FROM disponibile_l WHERE isbn = '" + isbn + "';";
            disponibileL = new ArrayList<>();
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                disponibileL.add(get(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileL;
    }

    @Override
    public ArrayList<DisponibileL> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<DisponibileL> disponibileLFinded = new ArrayList<>();
        String query = "";
        if (tipoRicerca.equalsIgnoreCase("coda")) {
            query = "SELECT coda,isbn FROM disponibile_l WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        }
        if (tipoRicerca.equalsIgnoreCase("isbn")) {
            query = "SELECT coda,isbn FROM disponibile_l WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        DisponibileL disponibileL;
        while (rs.next()) {
            disponibileL = get(rs.getInt(1), rs.getString(2));
            disponibileLFinded.add(disponibileL);
        }
        rs.close();
        return disponibileLFinded;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllT() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> getRicercaT(String modRicerca, String titoloRicerche) throws SQLException {
        return null;
    }

    @Override
    public void deleteT(ArrayList<String> elemento) throws SQLException {

    }

    @Override
    public void insertT(ArrayList<String> elemento) throws SQLException {

    }

    @Override
    public void updateT(ArrayList<String> elemento) throws SQLException {

    }

    @Override
    public List<DisponibileL> getAll() throws SQLException {
        ArrayList<DisponibileL> disponibileLFinded = new ArrayList<>();
        try {
            String query = "SELECT coda,isbn FROM disponibile_l;";
            ResultSet rs = connessione.executeSearch(query);
            DisponibileL disponibileL;
            while (rs.next()) {
                disponibileL = get(rs.getInt(1), rs.getString(2));
                disponibileLFinded.add(disponibileL);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileLFinded;
    }

    @Override
    public void insert(DisponibileL disponibileL) throws SQLException {
        try {
            String query = "INSERT INTO disponibile_l VALUES (" + disponibileL.getCoda() + ",'" + disponibileL.getIsbn() + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DisponibileL disponibileL) throws SQLException {
        try {
            String query = "UPDATE disponibile_l SET isbn = '" + disponibileL.getIsbn() + "' WHERE coda = " + disponibileL.getCoda() + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(DisponibileL disponibileL) throws SQLException {
        try {
            String query = "DELETE FROM disponibile_l WHERE coda = " + disponibileL.getCoda() + " AND isbn = '" + disponibileL.getIsbn() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
