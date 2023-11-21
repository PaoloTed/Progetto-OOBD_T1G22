package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.SerieDAO;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.bibliotecadigitale.Model.Utente.getUtente;

public class SerieDAOImpl implements SerieDAO {
    @Override
    public Serie get(String cods) {
        Serie serie = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM serie WHERE cods = '" + cods + "';";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                serie = new Serie();
                serie.setCodS(rs.getString(1));
                serie.setNome(rs.getString(2));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serie;
    }

    @Override
    public List<Serie> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Serie serie) {
        try {
            Connessione connessione = new Connessione();
            String cods = serie.getCodS();
            String nome = serie.getNome();
            String query = "INSERT INTO Serie VALUES ('" + cods + "','" + nome + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Serie serie) throws SQLException {

    }

    @Override
    public void delete(Serie serie) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String cods = serie.getCodS();
            String query = "DELETE FROM Serie WHERE cods = '" + cods + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
