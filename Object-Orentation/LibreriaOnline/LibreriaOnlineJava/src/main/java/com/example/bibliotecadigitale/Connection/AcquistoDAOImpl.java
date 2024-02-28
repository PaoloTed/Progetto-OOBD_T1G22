package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.AcquistoDAO;
import com.example.bibliotecadigitale.Model.Acquisto;

import java.sql.*;
import java.util.ArrayList;

public class AcquistoDAOImpl implements AcquistoDAO {

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
    public ArrayList<String> get(int coda) throws SQLException {
        ArrayList<String> acquisto = new ArrayList<>();
        String query = "SELECT * FROM acquisto WHERE coda = " + coda + ";";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            acquisto = rsToArrayList(rs);
        }
        rs.close();
        return acquisto;
    }

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> acquistoFinded = new ArrayList<>();
        String query = "SELECT * FROM acquisto;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> acquisto;
        while (rs.next()) {
            acquisto = rsToArrayList(rs);
            acquistoFinded.add(acquisto);
        }
        rs.close();
        return acquistoFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException, IllegalArgumentException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO acquisto VALUES (?,?,?,?,?);");
        //Inserimento valori non nullable
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.setString(3, strings.get(2));//tipo

        //Inserimento valori nullable
        if(strings.get(1) == null) {
            ps.setNull(2, Types.VARCHAR);
        } else {
            ps.setString(2, strings.get(1));//nome
        }
        if(strings.get(3) == null) {
            ps.setNull(4, Types.VARCHAR);
        } else {
            ps.setString(4, strings.get(3));//url
        }
        if(strings.get(4) == null) {
            ps.setNull(5, Types.VARCHAR);
        } else {
            ps.setString(5, strings.get(4));//indirizzo
        }
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException, IllegalArgumentException {
        PreparedStatement ps = conn.prepareStatement("UPDATE acquisto SET nome = ?, tipo = ?, url = ?, indirizzo = ? WHERE coda = ?;");
        //Inserimento valori non nullable
        ps.setString(2, strings.get(2));//tipo
        ps.setInt(5, Integer.parseInt(strings.get(0)));//coda

        //Inserimento valori nullable
        if(strings.get(1) == null) {
            ps.setNull(1, Types.VARCHAR);
        } else {
            ps.setString(1, strings.get(1));//nome
        }
        if(strings.get(3) == null) {
            ps.setNull(3, Types.VARCHAR);
        } else {
            ps.setString(3, strings.get(3));//url
        }
        if(strings.get(4) == null) {
            ps.setNull(4, Types.VARCHAR);
        } else {
            ps.setString(4, strings.get(4));//indirizzo
        }
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM acquisto WHERE coda = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//coda
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> acquistoFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("coda")) {
            query = "SELECT * FROM acquisto WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT * FROM acquisto WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> acquisto;
        while (rs.next()) {
            acquisto = rsToArrayList(rs);
            acquistoFinded.add(acquisto);
        }
        rs.close();
        return acquistoFinded;
    }
}
