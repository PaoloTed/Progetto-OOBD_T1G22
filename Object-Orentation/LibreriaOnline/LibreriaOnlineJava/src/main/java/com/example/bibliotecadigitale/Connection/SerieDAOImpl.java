package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.SerieDAO;
import com.example.bibliotecadigitale.Model.Serie;
import com.example.bibliotecadigitale.Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                serie.setCodS(rs.getInt(1));
                serie.setNome(rs.getString(2));
                serie.setNumLibri(rs.getInt(3));
                serie.setCompletata(rs.getBoolean(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serie;
    }

    public ArrayList<Serie> getRicerca(String tipoRicerca, String parolaChiave) {
        ArrayList<Serie> serieFinded = new ArrayList<>();
        String query = "";
        try {
            Connessione connessione = new Connessione();
            if (tipoRicerca.equalsIgnoreCase("numlibri") || tipoRicerca.equalsIgnoreCase("completata") || tipoRicerca.equalsIgnoreCase("cods")) {
                query = "SELECT cods FROM serie WHERE " + tipoRicerca + " = " + parolaChiave + ";";
            } else {
                query = "SELECT cods FROM serie WHERE " + tipoRicerca + " LIKE LOWER('%" + parolaChiave + "%');";
            }
            ResultSet rs = connessione.executeSearch(query);
            Serie serie;
            while (rs.next()) {
                serie = get(rs.getString(1));
                serieFinded.add(serie);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return serieFinded;
    }

    @Override
    public List<Serie> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Serie serie) {
        try {
            Connessione connessione = new Connessione();
            int cods = serie.getCodS();
            String nome = serie.getNome();
            String query = "INSERT INTO Serie VALUES ('" + cods + "','" + nome + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Serie serie) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            int cods = serie.getCodS();
            String nome = serie.getNome();
            boolean completata = serie.getCompletata();
            String query = "UPDATE Serie SET nome = '" + nome + "',completata = " + completata + " WHERE cods = '" + cods + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Serie serie) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            int cods = serie.getCodS();
            String query = "DELETE FROM Serie WHERE cods = '" + cods + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
