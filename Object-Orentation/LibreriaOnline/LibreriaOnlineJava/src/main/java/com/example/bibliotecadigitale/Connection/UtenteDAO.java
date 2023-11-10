package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.SupportStage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteDAO {

    private String email;
    private String password;

    public int getRowsExsistUtente(String emailUser, String passwordUser) throws IOException {
        int sizeTest = 0;
        try {
            Connection conn = Connessione.getConnection();
            String query = "SELECT * FROM utente WHERE email = '" + emailUser + "' AND password = '" + passwordUser  + "';";//query deve essere fstta dalla conessione
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()) {
                sizeTest = rs.getRow();
                if (sizeTest != 0) {
                    email = rs.getString("email");
                    password = rs.getString("password");
                }
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sizeTest;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
