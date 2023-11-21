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

    public int getRowsExsistUtenteEmailPassword(String emailUser, String passwordUser) {
        //TODO: aggiustare metodo e prendere direttamente i numero di righe senza fare il while
        int sizeTest = 0;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM utente WHERE email = '" + emailUser + "' AND password = '" + passwordUser + "';";//query deve essere fatta dalla connessione
            ResultSet rs = connessione.executeSearch(query);


            while (rs.next()) {
                sizeTest = rs.getRow();
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sizeTest;
    }

    public int getRowsExsistUtenteEmail(String emailUser) {
        int sizeTest = 0;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT count(*) FROM utente WHERE email = '" + emailUser + "';";//query deve essere fatta dalla connessione
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                sizeTest = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sizeTest;
    }

    public void insertUser(String emailUser, String passwordUser) {
        try {
            Connessione connessione = new Connessione();
            String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
            String query = "INSERT INTO Utente VALUES ('" + emailUser + "','" + passwordUser + "','" + date + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String searchData(String emailUser) {
        String data = "";
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT dataiscrizone FROM utente WHERE email = '" + emailUser + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                data = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public void updatePassword(String emailUser, String passwordUser) {
        try {
            Connessione connessione = new Connessione();
            String query = "UPDATE utente SET password = '" + passwordUser + "' WHERE email = '" + emailUser + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> searchPreferiti(String emailUser) {
        ArrayList<Integer> codiciPreferiti = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT cods FROM preferiti WHERE email = '" + emailUser + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                codiciPreferiti.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return codiciPreferiti;
    }

    public void insertPreferiti(String emailUser, int codiceSerie) {
        try {
            Connessione connessione = new Connessione();
            String query = "INSERT INTO preferiti VALUES ('" + emailUser + "','" + codiceSerie + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePreferiti(String emailUser, int codiceSerie) {
        try {
            Connessione connessione = new Connessione();
            String query = "DELETE FROM preferiti WHERE email = '" + emailUser + "' AND cods = '" + codiceSerie + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente get() throws SQLException {
        return null;
    }
    public Utente get(String emailUser) {
        Utente Utente;
        try{
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM utente WHERE email = '" + emailUser + "';";
            ResultSet rs = connessione.executeSearch(query);
            Utente = getUtente();
            while (rs.next()){
                Utente.setEmail(rs.getString(1));
                Utente.setPassword(rs.getString(2));
                Utente.setData(rs.getString(3));
            }
            rs.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Utente;
    }

    @Override
    public List<Utente> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Utente utente) {
        try {
            Connessione connessione = new Connessione();
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

    }

    @Override
    public void delete(Utente utente) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String emailUser = utente.getEmail();
            String query = "DELETE FROM Utente WHERE EMAIL ='" + emailUser + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
