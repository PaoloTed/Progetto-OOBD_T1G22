package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.SerieDAO;
import com.example.bibliotecadigitale.Model.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieDAOImpl implements SerieDAO {
    private final Connessione connessione = new Connessione();
    private final Connection conn = Connessione.getConnection();

    private ArrayList<String> rsToArrayList(ResultSet rs, int numCampi) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 1; i <= numCampi; i++) {
            array.add(rs.getString(i));
        }
        return array;
    }
    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> serieFinded = new ArrayList<>();
        String query = "SELECT * FROM serie;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> serie;
        while (rs.next()) {
            serie = rsToArrayList(rs, 2);
            serieFinded.add(serie);
        }
        rs.close();
        return serieFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO serie VALUES (?,?,?,?);");
        //Inserimento valori non nullable
        ps.setInt(1, Integer.parseInt(strings.get(0)));//cods
        ps.setString(2, strings.get(1));//nome

        //Inserimento valori nullable
        if (strings.get(2) == null) {
            ps.setNull(3, java.sql.Types.INTEGER);
        } else {
            ps.setInt(3, Integer.parseInt(strings.get(2)));//numerolibri
        }
        if (strings.get(3) == null) {
            ps.setNull(4, java.sql.Types.BOOLEAN);
        } else {
            ps.setBoolean(4, Boolean.parseBoolean(strings.get(3)));//completata
        }
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE serie SET nome = ?, numlibri = ?, completata = ? WHERE cods = ?;");
        //Inserimento valori non nullable
        ps.setString(1, strings.get(1));//nome
        ps.setInt(4, Integer.parseInt(strings.get(0)));//cods
        //Inserimento valori nullable
        if (strings.get(2) == null) {
            ps.setNull(3, java.sql.Types.INTEGER);
        } else {
            ps.setInt(3, Integer.parseInt(strings.get(2)));//numerolibri
        }
        if (strings.get(3) == null) {
            ps.setNull(4, java.sql.Types.BOOLEAN);
        } else {
            ps.setBoolean(4, Boolean.parseBoolean(strings.get(3)));//completata
        }

        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM serie WHERE cods = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//cods
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> serieFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("completata")||tipoRicerca.equalsIgnoreCase("cods")||tipoRicerca.equalsIgnoreCase("numlibri")) {
            query = "SELECT * FROM serie WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT * FROM serie WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> serie;
        while (rs.next()) {
            serie = rsToArrayList(rs, 4);
            serieFinded.add(serie);
        }
        rs.close();
        return serieFinded;
    }

    @Override
    public ArrayList<String> get(int cods) throws SQLException {
        ArrayList<String> serie = new ArrayList<>();
        String query = "SELECT * FROM serie WHERE cods = " + cods + ";";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            serie = rsToArrayList(rs, 4);
        }
        rs.close();
        return serie;
    }
}
