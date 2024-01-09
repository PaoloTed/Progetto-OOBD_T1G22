package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.Model.Conferenza;
import com.example.bibliotecadigitale.Model.Rivista;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RivistaDAOImpl
{

    public Rivista get(String data, String nome) {
        Rivista rivista = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM rivista WHERE nome = '" + nome + "' AND data = '" + data + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                rivista = new Rivista();
                rivista.setNome(rs.getString("nome"));
                rivista.setData(rs.getString("data"));
                rivista.setResponsabile(rs.getString("responsabile"));
                rivista.setArgomento(rs.getString("argomento"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rivista;
    }
}
