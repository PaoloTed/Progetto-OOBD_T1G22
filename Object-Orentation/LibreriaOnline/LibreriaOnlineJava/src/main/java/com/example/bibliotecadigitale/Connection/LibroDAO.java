package com.example.bibliotecadigitale.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroDAO
{

    public ResultSet searchData(String tipoRicerca, String ricerca) {
        ResultSet rs = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT libro FROM utente WHERE " + tipoRicerca + " = '" + ricerca + "';";
            rs = connessione.executeSearch(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}
