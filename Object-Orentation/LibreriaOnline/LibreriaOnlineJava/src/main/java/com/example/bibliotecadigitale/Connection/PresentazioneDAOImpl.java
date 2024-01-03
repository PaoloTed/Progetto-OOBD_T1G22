package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.Model.Presentazione;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PresentazioneDAOImpl
{

    public Presentazione get(String codsP) {
        Presentazione presentazione = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM serie WHERE cods = '" + codsP + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                presentazione = new Presentazione();
                presentazione.setCodP(rs.getString(1));
                presentazione.setNome(rs.getString(2));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return presentazione;
    }
}
