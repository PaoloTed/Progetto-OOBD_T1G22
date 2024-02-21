package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.ConferenzaDAO;
import com.example.bibliotecadigitale.Model.Conferenza;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConferenzaDAOImpl implements ConferenzaDAO {
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
    public ArrayList<String> get(int codC) throws SQLException {
        ArrayList<String> conferenzaFinded = new ArrayList<>();
        String query = "SELECT * FROM conferenza WHERE codC = " + codC + ";";
        ResultSet rs = connessione.executeSearch(query);
        if (rs.next()) {
            conferenzaFinded = rsToArrayList(rs);
        }
        rs.close();
        return conferenzaFinded;
    }

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> conferenzaFinded = new ArrayList<>();
        String query = "SELECT * FROM conferenza;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> conferenza;
        while (rs.next()) {
            conferenza = rsToArrayList(rs);
            conferenzaFinded.add(conferenza);
        }
        rs.close();
        return conferenzaFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO conferenza VALUES (?,?,?,?,?,?,?);");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//codC
        ps.setString(2, strings.get(1));//nome
        ps.setString(3, strings.get(2));//struttura
        ps.setString(4, strings.get(3));//indirizzo
        ps.setDate(5, Date.valueOf(strings.get(4)));//dataInizio
        ps.setDate(6, Date.valueOf(strings.get(5)));//dataFine
        ps.setString(7, strings.get(6));//responsabile
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE conferenza SET codC = ?, nome = ?, struttura = ?, indirizzo = ?, dataInizio = ?, dataFine = ?, responsabile = ? WHERE codC = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//codC
        ps.setString(2, strings.get(1));//nome
        ps.setString(3, strings.get(2));//struttura
        ps.setString(4, strings.get(3));//indirizzo
        ps.setDate(5, Date.valueOf(strings.get(4)));//dataInizio
        ps.setDate(6, Date.valueOf(strings.get(5)));//dataFine
        ps.setString(7, strings.get(6));//responsabile
        ps.setInt(8, Integer.parseInt(strings.get(0)));//codC
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM conferenza WHERE codC = ?;");
        ps.setInt(1, Integer.parseInt(strings.get(0)));//codC
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> conferenzaFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("codC")) {
            query = "SELECT * FROM conferenza WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            if (tipoRicerca.equalsIgnoreCase("dataI") || tipoRicerca.equalsIgnoreCase("dataF")) {
                query = "SELECT * FROM conferenza WHERE " + tipoRicerca + " = '" + parolaChiave + "';";
            } else {
                query = "SELECT * FROM conferenza WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
            }
        }
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> conferenza;
        while (rs.next()) {
            conferenza = rsToArrayList(rs);
            conferenzaFinded.add(conferenza);
        }
        rs.close();
        return conferenzaFinded;
    }
}
