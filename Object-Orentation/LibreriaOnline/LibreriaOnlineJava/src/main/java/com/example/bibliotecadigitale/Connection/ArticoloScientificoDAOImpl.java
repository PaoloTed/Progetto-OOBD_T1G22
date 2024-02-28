package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.ArticoloScientificoDAO;
import com.example.bibliotecadigitale.Model.ArticoloScientifico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticoloScientificoDAOImpl implements ArticoloScientificoDAO {
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
    public ArrayList<String> get(String doi) throws SQLException {
        ArrayList<String> articolo = new ArrayList<>();
        String query = "SELECT * FROM articolo_scientifico WHERE doi = '" + doi + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            articolo = rsToArrayList(rs);
        }
        rs.close();
        return articolo;
    }

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        ArrayList<ArrayList<String>> articoloFinded = new ArrayList<>();
        String query = "SELECT * FROM articolo_scientifico;";
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> articolo;
        while (rs.next()) {
            articolo = rsToArrayList(rs);
            articoloFinded.add(articolo);
        }
        rs.close();
        return articoloFinded;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException, IllegalArgumentException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO articolo_scientifico VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
        //Inserimento valori non nullable
        ps.setString(1, strings.get(0));//doi
        ps.setString(2, strings.get(1));//titolo
        ps.setString(3, strings.get(2));//genere
        ps.setInt(4, Integer.parseInt(strings.get(3)));//numeroPagine
        if (strings.get(5) != null) {
            ps.setString(6, strings.get(5).replace("'", "''"));//descrizione
        }
        ps.setString(7, strings.get(6));//fruizione
        ps.setString(8, strings.get(7));//editore
        ps.setString(9, strings.get(8));//autore
        ps.setString(10, strings.get(9));//lingua

        //Inserimento valori nullable
        if (strings.get(4) == null) {
            ps.setNull(5, Types.DATE);
        } else {
            ps.setDate(5, Date.valueOf(strings.get(4)));//dataUscita
        }
        if (strings.get(10) == null || strings.get(10).equals("null")) {
            ps.setNull(11, Types.INTEGER);
        } else {
            ps.setInt(11, Integer.parseInt(strings.get(10)));//conferenza
        }
        if (strings.get(11) == null) {
            ps.setNull(12, Types.VARCHAR);
        } else {
            ps.setString(12, strings.get(11));//rivista
        }
        if (strings.get(12) == null) {
            ps.setNull(13, Types.VARCHAR);
        } else {
            ps.setDate(13, Date.valueOf(strings.get(12)));//datarivista
        }
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException, IllegalArgumentException {
        PreparedStatement ps = conn.prepareStatement("UPDATE articolo_scientifico SET titolo = ?, genere = ?, numpagine = ?, dataUscita = ?, descrizione = ?, fruizione = ?, editore = ?, autore = ?, lingua = ?, conferenza = ?, nomer = ?, datar = ? WHERE doi = ?;");
        //Inserimento valori non nullable
        ps.setString(13, strings.get(0));//doi
        ps.setString(1, strings.get(1));//titolo
        ps.setString(2, strings.get(2));//genere
        ps.setInt(3, Integer.parseInt(strings.get(3)));//numeroPagine
        if (strings.get(5) != null) {
            ps.setString(5, strings.get(5).replace("'", "''"));//descrizione
        }
        ps.setString(6, strings.get(6));//fruizione
        ps.setString(7, strings.get(7));//editore
        ps.setString(8, strings.get(8));//autore
        ps.setString(9, strings.get(9));//lingua

        //Inserimento valori nullable
        if (strings.get(4) == null) {
            ps.setNull(4, Types.DATE);
        } else {
            ps.setDate(4, Date.valueOf(strings.get(4)));//dataUscita
        }
        if (strings.get(10) == null) {
            ps.setNull(10, Types.INTEGER);
        } else {
            ps.setInt(10, Integer.parseInt(strings.get(10)));//conferenza
        }
        if (strings.get(11) == null) {
            ps.setNull(11, Types.VARCHAR);
        } else {
            ps.setString(11, strings.get(11));//rivista
        }
        if (strings.get(12) == null) {
            ps.setNull(12, Types.VARCHAR);
        } else {
            ps.setDate(12, Date.valueOf(strings.get(12)));//datarivista
        }
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM articolo_scientifico WHERE doi = ?;");
        ps.setString(1, strings.get(0));
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArrayList<String>> articoloFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("numpagine") || (tipoRicerca.equalsIgnoreCase("conferenza"))) {
            query = "SELECT * FROM articolo_scientifico WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            if (tipoRicerca.equalsIgnoreCase("dataUscita") || (tipoRicerca.equalsIgnoreCase("datar"))) {
                query = "SELECT * FROM articolo_scientifico WHERE " + tipoRicerca + " = '" + parolaChiave + "';";
            } else {
                query = "SELECT * FROM articolo_scientifico WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
            }
        }
        connessione.executeSearch(query);
        ResultSet rs = connessione.executeSearch(query);
        ArrayList<String> articolo;
        while (rs.next()) {
            articolo = rsToArrayList(rs);
            articoloFinded.add(articolo);
        }
        rs.close();
        return articoloFinded;
    }
}
