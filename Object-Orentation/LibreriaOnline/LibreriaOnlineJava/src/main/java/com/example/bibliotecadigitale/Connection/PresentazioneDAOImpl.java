package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.PresentazioneDAO;
import com.example.bibliotecadigitale.Model.Presentazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PresentazioneDAOImpl implements PresentazioneDAO {
    private final Connessione connessione = new Connessione();
    private final Connection conn = Connessione.getConnection();

    private ArrayList<String> rsToArrayList(ResultSet rs) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            array.add(rs.getString(i));
        }
        return array;
    }

    public ArrayList<String> get(String strings) throws SQLException {
        ArrayList<String> presentazione = new ArrayList<>();
        String query = "SELECT * FROM presentazione WHERE codP = " + strings + ";";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            presentazione = rsToArrayList(rs);
        }
        rs.close();
        return presentazione;
    }

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> presentazioneFinded = new ArrayList<>();
        String query = "SELECT * FROM presentazione;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> presentazione;
        while (rs.next()) {
            presentazione = rsToArrayList(rs);
            presentazioneFinded.add(presentazione);
        }
        rs.close();
        return presentazioneFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException, IllegalArgumentException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO presentazione VALUES (?,?,?,?,?);");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//codP
        ps.setString(2, strings.get(1));//nome
        ps.setString(3, strings.get(2));//indirizzo
        ps.setDate(4, Date.valueOf(strings.get(3)));//data
        ps.setString(5, strings.get(4));//tipo
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException, IllegalArgumentException{
        PreparedStatement ps = conn.prepareStatement("UPDATE presentazione SET nome = ?, indirizzo = ?, datapresentazione = ?, tipo = ? WHERE codP = ?;");
        ps.setString(1, strings.get(1));//nome
        ps.setString(2, strings.get(2));//indirizzo
        ps.setDate(3, Date.valueOf(strings.get(3)));//data
        ps.setString(4, strings.get(4));//tipo
        ps.setInt(5, Integer.parseInt(strings.get(0)));//codP
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM presentazione WHERE codP = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//codP
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> presentazioneFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("codP")) {
            query = "SELECT * FROM presentazione WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            if (tipoRicerca.equalsIgnoreCase("data")) {
                query = "SELECT * FROM presentazione WHERE " + tipoRicerca + " = '" + parolaChiave + "';";
            } else {
                query = "SELECT * FROM presentazione WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
            }
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> presentazione;
        while (rs.next()) {
            presentazione = rsToArrayList(rs);
            presentazioneFinded.add(presentazione);
        }
        rs.close();
        return presentazioneFinded;
    }

    @Override
    public ArrayList<String> get(int codP) throws SQLException{
        ArrayList<String> presentazione = new ArrayList<>();
        String query = "SELECT * FROM presentazione WHERE codP = " + codP + ";";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            presentazione = rsToArrayList(rs);
        }
        rs.close();
        return presentazione;
    }
}
