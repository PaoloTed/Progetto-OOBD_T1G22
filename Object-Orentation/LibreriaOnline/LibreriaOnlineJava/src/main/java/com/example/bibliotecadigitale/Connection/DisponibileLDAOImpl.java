package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileLDAO;
import com.example.bibliotecadigitale.Model.DisponibileL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileLDAOImpl implements DisponibileLDAO {
    private final Connessione connessione = new Connessione();
    private final java.sql.Connection conn = Connessione.getConnection();

    private ArrayList<String> rsToArrayList(ResultSet rs) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            array.add(rs.getString(i));
        }
        return array;
    }

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> disponibileLFinded = new ArrayList<>();
        String query = "SELECT * FROM disponibile_l;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> disponibileL;
        while (rs.next()) {
            disponibileL = rsToArrayList(rs);
            disponibileLFinded.add(disponibileL);
        }
        rs.close();
        return disponibileLFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException, IllegalArgumentException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO disponibile_l VALUES (?,?);");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.setString(2, strings.get(1));//isbn
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException, IllegalArgumentException{
        //TODO non deve essere implementato???
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM disponibile_l WHERE coda = ? AND isbn = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.setString(2, strings.get(1));//isbn
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> disponibileLFinded = new ArrayList<>();
        String query = "SELECT * FROM disponibile_l WHERE ";
        if (tipoRicerca.equals("coda")) {
            query = "SELECT * FROM disponibile_l WHERE coda = " + parolaChiave + ";";
        } else if (tipoRicerca.equals("isbn")) {
            query = "SELECT * FROM disponibile_l WHERE isbn = '" + parolaChiave + "';";
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> disponibileL;
        while (rs.next()) {
            disponibileL = rsToArrayList(rs);
            disponibileLFinded.add(disponibileL);
        }
        rs.close();
        return disponibileLFinded;
    }

    @Override
    public ArrayList<String> get(int coda, String isbn) throws SQLException {
        //TODO non deve essere implementato???
        ArrayList<String> disponibileL = new ArrayList<>();
        String query = "SELECT * FROM disponibile_l WHERE coda = " + coda + " AND isbn = '" + isbn + "';";
        ResultSet rs = connessione.executeSearch(query);
        if (rs.next()) {
            disponibileL = rsToArrayList(rs);
        }
        rs.close();
        return disponibileL;
    }

    public ArrayList<ArrayList<String>> getAcquisti(String isbn) throws SQLException {
        ArrayList<ArrayList<String>> disponibileLFinded = new ArrayList<>();
        ArrayList<String> disponibileL;
        String query = "SELECT * FROM disponibile_l WHERE isbn = '" + isbn + "';";
        ResultSet rs = connessione.executeSearch(query);
        disponibileLFinded = new ArrayList<>();
        while (rs.next()) {
            disponibileL = rsToArrayList(rs);
            disponibileLFinded.add(disponibileL);
        }
        rs.close();
        return disponibileLFinded;
    }
}
