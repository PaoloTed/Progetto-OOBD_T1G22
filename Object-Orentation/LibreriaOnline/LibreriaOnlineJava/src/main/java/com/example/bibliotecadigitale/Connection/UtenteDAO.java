package com.example.bibliotecadigitale.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class UtenteDAO {

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
            connessione.executeInsert(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String searchData(String emailUser) {
        String data = "";
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT dataiscrizone FROM utente WHERE email = '" + emailUser + "';";//query deve essere fatta dalla connessione
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
}
