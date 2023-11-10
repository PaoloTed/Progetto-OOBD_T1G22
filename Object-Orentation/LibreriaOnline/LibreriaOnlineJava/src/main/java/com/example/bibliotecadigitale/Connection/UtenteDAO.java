package com.example.bibliotecadigitale.Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class UtenteDAO {

    private String email;
    private String password;

    public int getRowsExsistUtenteEmailPassword(String emailUser, String passwordUser) {
        int sizeTest = 0;
        try {
            Connection conn = Connessione.getConnection();
            String query = "SELECT * FROM utente WHERE email = '" + emailUser + "' AND password = '" + passwordUser + "';";//query deve essere fatta dalla connessione
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                sizeTest = rs.getRow();
                if (sizeTest != 0) {
                    email = rs.getString("email");
                    password = rs.getString("password");
                }
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
            Connection conn = Connessione.getConnection();
            String query = "SELECT count(*) FROM utente WHERE email = '" + emailUser + "';";//query deve essere fatta dalla connessione
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                sizeTest = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sizeTest;
    }
    public void insertUsername(String emailUser, String passwordUser) {
        try {
            Connection conn = Connessione.getConnection();
            String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
            //La data deve essere effettuata da connessione
            String query = "INSERT INTO Utente VALUES ('" + emailUser + "','" + passwordUser + "','" + date + "');";
            Statement stat = conn.createStatement();
            stat.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
