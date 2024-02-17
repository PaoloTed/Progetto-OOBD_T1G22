package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.UtenteDAO;
import com.example.bibliotecadigitale.Model.Utente;
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

    @Override
    public Utente get(String emailUser) {
        Utente utente = null;
        try {
            String query = "SELECT * FROM utente WHERE email = '" + emailUser + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                utente = getUtente();
                utente.setEmail(rs.getString(1));
                utente.setPassword(rs.getString(2));
                utente.setData(rs.getString(3));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utente;
    }

    @Override
    public ArrayList<Utente> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<Utente> utenteFinded = new ArrayList<>();

        String query = "SELECT email FROM utente WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        ResultSet rs = connessione.executeSearch(query);
        Utente utente;
        while (rs.next()) {
            utente = get(rs.getString(1));
            utenteFinded.add(utente);
        }
        rs.close();
        return utenteFinded;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllT() {
        return null;
    }

    @Override
    public List<Utente> getAll() throws SQLException {
        try {
            String query = "SELECT email FROM utente;";
            ResultSet rs = connessione.executeSearch(query);
            ArrayList<Utente> utenteFinded = new ArrayList<>();
            Utente utente;
            while (rs.next()) {
                utente = get(rs.getString(1));
                utenteFinded.add(utente);
            }
            rs.close();
            return utenteFinded;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Utente utente) {
        try {
            String emailUser = utente.getEmail();
            String passwordUser = utente.getPassword();
            String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
            String query = "INSERT INTO Utente VALUES ('" + emailUser + "','" + passwordUser + "','" + date + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Utente utente) throws SQLException {
        try {
            String query = "UPDATE Utente SET password = '" + utente.getPassword() + "' WHERE email = '" + utente.getEmail() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Utente utente) {
        try {
            String query = "DELETE FROM Utente WHERE EMAIL ='" + utente.getEmail() + "';";
            utente.setEmail(null);
            utente.setPassword(null);
            utente.setData(null);
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
