package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileSDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisponibileSDAOImpl implements DisponibileSDAO {
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
        ArrayList<ArrayList<String>> disponibileSFinded = new ArrayList<>();
        String query = "SELECT * FROM disponibile_s;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> disponibileS;
        while (rs.next()) {
            disponibileS = rsToArrayList(rs);
            disponibileSFinded.add(disponibileS);
        }
        rs.close();
        return disponibileSFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO disponibile_s VALUES (?,?);");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.setInt(2, Integer.parseInt(strings.get(1)));//cods
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException {
        //TODO Non deve essere implementato??
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        //TODO Non deve essere implementato??
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> disponibileSFinded = new ArrayList<>();
        String query = "SELECT * FROM disponibile_s WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> disponibileS;
        while (rs.next()) {
            disponibileS = rsToArrayList(rs);
            disponibileSFinded.add(disponibileS);
        }
        rs.close();
        return disponibileSFinded;
    }

    @Override
    public ArrayList<String> get(int coda, int cods) throws SQLException {
        //TODO Non deve essere implementato??
        ArrayList<String> disponibileS = new ArrayList<>();
        String query = "SELECT * FROM disponibile_s WHERE coda = " + coda + " AND cods = " + cods + ";";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            disponibileS = rsToArrayList(rs);
        }
        rs.close();
        return disponibileS;
    }

    public ArrayList<ArrayList<String>> getAcquisti(int coda) throws SQLException {
        ArrayList<ArrayList<String>> disponibileSFinded = new ArrayList<>();
        ArrayList<String> disponibileS;
        String query = "SELECT * FROM disponibile_s WHERE isbn = '" + coda + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            disponibileS = rsToArrayList(rs);
            disponibileSFinded.add(disponibileS);
        }
        rs.close();
        return disponibileSFinded;
    }
}
