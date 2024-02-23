package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.UtenteDAO;
import com.example.bibliotecadigitale.Model.Utente;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.bibliotecadigitale.Model.Utente.getUtente;

public class UtenteDAOImpl implements UtenteDAO {

    private final Connessione connessione = new Connessione();
    private final Connection conn = Connessione.getConnection();

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO utente VALUES (?,?,?);");
        ps.setString(1, strings.get(0));//email
        ps.setString(2, strings.get(1));//password
        ps.setDate(3, Date.valueOf(strings.get(2)));//data
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE utente SET password = ? WHERE email = ?;");
        ps.setString(1, strings.get(1));//password
        ps.setString(2, strings.get(0));//email
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM utente WHERE email = ?;");
        ps.setString(1, strings.get(0));//email
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> get(String emailUser) throws SQLException {
        ArrayList<String> utente = new ArrayList<>();
        String query = "SELECT * FROM utente WHERE email = '" + emailUser + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            utente.add(rs.getString(1));
            utente.add(rs.getString(2));
            utente.add(rs.getString(3));
        }
        return utente;
    }

    public void updatePassword(String emailUser, String passwordUser) throws SQLException {
        String query = "UPDATE utente SET password = '" + passwordUser + "' WHERE email = '" + emailUser + "';";
        connessione.executeUpdate(query);
    }

    public ArrayList<Integer> searchPreferiti(String emailUser) throws SQLException {
        ArrayList<Integer> codiciPreferiti = new ArrayList<>();
        String query = "SELECT cods FROM preferiti WHERE email = '" + emailUser + "';";
        ResultSet rs = connessione.executeSearch(query);
        while (rs.next()) {
            codiciPreferiti.add((rs.getInt(1)));
        }
        return codiciPreferiti;
    }

    public void insertPreferiti(String emailUser, int codiceSerie) throws SQLException {
        String query = "INSERT INTO preferiti VALUES ('" + emailUser + "','" + codiceSerie + "');";
        connessione.executeUpdate(query);
    }

    public void deletePreferiti(String emailUser, int codiceSerie) throws SQLException {
        String query = "DELETE FROM preferiti WHERE email = '" + emailUser + "' AND cods = '" + codiceSerie + "';";
        connessione.executeUpdate(query);
    }

    public boolean esisteUtente(String emailUser) throws SQLException {
        String query = "SELECT * FROM utente WHERE email = '" + emailUser + "';";
        ResultSet rs = connessione.executeSearch(query);
        return rs.next();
    }
}
