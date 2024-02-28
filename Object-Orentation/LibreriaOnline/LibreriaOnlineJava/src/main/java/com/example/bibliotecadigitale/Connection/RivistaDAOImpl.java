package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.RivistaDAO;
import com.example.bibliotecadigitale.Model.Rivista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RivistaDAOImpl implements RivistaDAO {
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
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> rivistaFinded = new ArrayList<>();
        String query = "SELECT * FROM rivista;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> rivista;
        while (rs.next()) {
            rivista = rsToArrayList(rs);
            rivistaFinded.add(rivista);
        }
        rs.close();
        return rivistaFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException, IllegalArgumentException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO rivista VALUES (?,?,?,?);");
        ps.setString(1, strings.get(0));//nome
        ps.setDate(2, java.sql.Date.valueOf(strings.get(1)));//data
        ps.setString(3, strings.get(2));//responsabile
        ps.setString(4, strings.get(3));//argomento
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException, IllegalArgumentException{
        PreparedStatement ps = conn.prepareStatement("UPDATE rivista SET data = ?, responsabile = ?, argomento = ? WHERE nome = ?;");
        ps.setDate(1, java.sql.Date.valueOf(strings.get(1)));//data
        ps.setString(2, strings.get(2));//responsabile
        ps.setString(3, strings.get(3));//argomento
        ps.setString(4, strings.get(0));//nome
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM rivista WHERE nome = ?;");
        ps.setString(1, strings.get(0));//nome
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> rivistaFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("data")) {
            query = "SELECT * FROM rivista WHERE " + tipoRicerca + " = '" + parolaChiave + "';";
        } else {
            query = "SELECT * FROM rivista WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> rivista;
        while (rs.next()) {
            rivista = rsToArrayList(rs);
            rivistaFinded.add(rivista);
        }
        rs.close();
        return rivistaFinded;
    }

    @Override
    public ArrayList<String> get(String nome, String data) throws SQLException {
        ArrayList<String> rivista = new ArrayList<>();
        String query = "SELECT * FROM rivista WHERE nome = '" + nome + "' AND data = '" + data + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            rivista = rsToArrayList(rs);
        }
        rs.close();
        return rivista;
    }
}
