package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileADAO;
import com.example.bibliotecadigitale.Model.DisponibileA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileADAOImpl implements DisponibileADAO {
    private final Connessione connessione = new Connessione();
    private final java.sql.Connection conn = Connessione.getConnection();

    private ArrayList<String> rsToArrayList(ResultSet rs, int numCampi) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 1; i <= numCampi; i++) {
            array.add(rs.getString(i));
        }
        return array;
    }

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> disponibileAFinded = new ArrayList<>();
        String query = "SELECT * FROM disponibile_a;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> disponibileA;
        while (rs.next()) {
            disponibileA = rsToArrayList(rs, 2);
            disponibileAFinded.add(disponibileA);
        }
        rs.close();
        return disponibileAFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO disponibile_a VALUES (?,?);");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.setString(2, strings.get(1));//doi
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException {
        //TODO non deve essere implementato???
        PreparedStatement ps = conn.prepareStatement("UPDATE disponibile_a SET coda = ?, doi = ? WHERE coda = ? AND doi = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.setString(2, strings.get(1));//doi
        ps.setInt(3, Integer.parseInt(strings.get(0)));//coda
        ps.setString(4, strings.get(1));//doi
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM disponibile_a WHERE coda = ? AND doi = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.setString(2, strings.get(1));//doi
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> disponibileAFinded = new ArrayList<>();
        String query = "SELECT * FROM disponibile_a WHERE ";
        if(tipoRicerca.equals("coda")) {
            query = "SELECT * FROM disponibile_a WHERE coda = " + parolaChiave + ";";
        } else if(tipoRicerca.equals("doi")) {
            query = "SELECT * FROM disponibile_a WHERE doi = '" + parolaChiave + "';";
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> disponibileA;
        while (rs.next()) {
            disponibileA = rsToArrayList(rs, 2);
            disponibileAFinded.add(disponibileA);
        }
        rs.close();
        return disponibileAFinded;
    }

    @Override
    public ArrayList<String> get(int coda, String doi) throws SQLException {
        //TODO non deve essere implementato???
        ArrayList<String> disponibileA = new ArrayList<>();
        String query = "SELECT * FROM disponibile_a WHERE coda = " + coda + " AND doi = '" + doi + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            disponibileA = rsToArrayList(rs, 2);
        }
        rs.close();
        return disponibileA;
    }

    public ArrayList<ArrayList<String>> getAcquisti(String doi) throws SQLException {
        ArrayList<ArrayList<String>>  disponibileAFinded = new ArrayList<>();
        ArrayList<String> disponibileA;
        try {
            String query = "SELECT * FROM disponibile_a WHERE doi = '" + doi + "';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileAFinded = new ArrayList<>();
            while (rs.next()) {
                disponibileA = rsToArrayList(rs, 2);
                disponibileAFinded.add(disponibileA);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileAFinded;
    }
}
