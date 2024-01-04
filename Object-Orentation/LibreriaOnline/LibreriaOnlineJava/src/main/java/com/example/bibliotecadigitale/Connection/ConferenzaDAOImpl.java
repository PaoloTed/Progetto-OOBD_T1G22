package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.Model.Conferenza;
import com.example.bibliotecadigitale.Model.Serie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConferenzaDAOImpl
{
    public Conferenza get(int codC) {
        Conferenza conferenza = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM conferenza WHERE codC = '" + codC + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                conferenza = new Conferenza();
                conferenza.setCodC(Integer.parseInt(rs.getString("codC")));
                conferenza.setNome(rs.getString("nome"));
                conferenza.setDataI(rs.getString("dataI"));
                conferenza.setDataF(rs.getString("dataF"));
                conferenza.setIndirizzo(rs.getString("indirizzo"));
                conferenza.setResponsabile(rs.getString("responsabile"));
                conferenza.setStruttura(rs.getString("struttura"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conferenza;
    }
}
