package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileADAO;
import com.example.bibliotecadigitale.Model.DisponibileA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileADAOImpl implements DisponibileADAO {
    private final Connessione connessione = new Connessione();

    @Override
    public DisponibileA get(int coda, String doi) throws SQLException {
        DisponibileA disponibileA;
        try {
            String query = "SELECT coda,doi FROM disponibile_a WHERE coda = " + coda + " AND doi = '" + doi + "';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileA = new DisponibileA();
            while (rs.next()) {
                disponibileA.setCoda(rs.getInt(1));
                disponibileA.setDoi(rs.getString(2));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileA;
    }

    public ArrayList<DisponibileA> getAcquisti(String doi) throws SQLException {
        ArrayList<DisponibileA> disponibileA;
        try {
            String query = "SELECT * FROM disponibile_a WHERE doi = '" + doi + "';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileA = new ArrayList<>();
            while (rs.next()) {
                disponibileA.add(get(rs.getInt(1), rs.getString(2)));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileA;
    }

    @Override
    public ArrayList<DisponibileA> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<DisponibileA> disponibileAFinded = new ArrayList<>();
        String query = "";

        if (tipoRicerca.equalsIgnoreCase("coda")) {
            query = "SELECT coda,doi FROM disponibile_a WHERE coda = " + parolaChiave + ";";
        }
        if (tipoRicerca.equalsIgnoreCase("doi")) {
            query = "SELECT coda,doi FROM disponibile_a WHERE doi LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        DisponibileA disponibileA;
        while (rs.next()) {
            disponibileA = get(rs.getInt(1), rs.getString(2));
            disponibileAFinded.add(disponibileA);
        }
        rs.close();

        return disponibileAFinded;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllT() {
        return null;
    }

    @Override
    public List<DisponibileA> getAll() throws SQLException {
        ArrayList<DisponibileA> disponibileAFinded = new ArrayList<>();
        try {
            String query = "SELECT coda,doi FROM disponibile_a;";
            ResultSet rs = connessione.executeSearch(query);
            DisponibileA disponibileA;
            while (rs.next()) {
                disponibileA = get(rs.getInt(1), rs.getString(2));
                disponibileAFinded.add(disponibileA);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileAFinded;
    }

    @Override
    public void insert(DisponibileA disponibileA) throws SQLException {
        try {
            String query = "INSERT INTO disponibile_a VALUES (" + disponibileA.getCoda() + ",'" + disponibileA.getDoi() + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DisponibileA disponibileA) throws SQLException {
        try {
            String query = "UPDATE disponibile_a SET coda = " + disponibileA.getCoda() + ", doi = '" + disponibileA.getDoi() + "' WHERE coda = " + disponibileA.getCoda() + " AND doi = '" + disponibileA.getDoi() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(DisponibileA disponibileA) throws SQLException {
        try {
            String query = "DELETE FROM disponibile_a WHERE coda = " + disponibileA.getCoda() + " AND doi = '" + disponibileA.getDoi() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
