package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.UtenteDAO;
import com.example.bibliotecadigitale.Model.Utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException {

    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {

    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> get(String emailUser) throws SQLException {
        ArrayList<String> utente = new ArrayList<>();
        try {
            String query = "SELECT * FROM utente WHERE email = '" + emailUser + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                utente.add(rs.getString(1));
                utente.add(rs.getString(2));
                utente.add(rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utente;
    }

    public void updatePassword(String emailUser, String passwordUser) {
        try {
            String query = "UPDATE utente SET password = '" + passwordUser + "' WHERE email = '" + emailUser + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> searchPreferiti(String emailUser) {
        ArrayList<Integer> codiciPreferiti = new ArrayList<>();
        try {
            String query = "SELECT cods FROM preferiti WHERE email = '" + emailUser + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                codiciPreferiti.add((rs.getInt(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return codiciPreferiti;
    }

    public void insertPreferiti(String emailUser, int codiceSerie) {
        try {
            String query = "INSERT INTO preferiti VALUES ('" + emailUser + "','" + codiceSerie + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePreferiti(String emailUser, int codiceSerie) {
        try {
            String query = "DELETE FROM preferiti WHERE email = '" + emailUser + "' AND cods = '" + codiceSerie + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
