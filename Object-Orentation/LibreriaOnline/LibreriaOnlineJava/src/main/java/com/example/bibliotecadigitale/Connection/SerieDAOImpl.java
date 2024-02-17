package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.SerieDAO;
import com.example.bibliotecadigitale.Model.Serie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieDAOImpl implements SerieDAO {
    private final Connessione connessione = new Connessione();

    @Override
    public Serie get(int cods) {
        Serie serie = null;
        try {
            String query = "SELECT * FROM serie WHERE cods = " + cods + ";";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                serie = new Serie();
                serie.setCods(rs.getInt(1));
                serie.setNome(rs.getString(2));
                serie.setNumlibri(rs.getInt(3));
                serie.setCompletata(rs.getBoolean(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serie;
    }

    @Override
    public ArrayList<Serie> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<Serie> serieFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("numlibri") || tipoRicerca.equalsIgnoreCase("completata") || tipoRicerca.equalsIgnoreCase("cods")) {
            query = "SELECT cods FROM serie WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT cods FROM serie WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        Serie serie;
        while (rs.next()) {
            serie = get(rs.getInt(1));
            serieFinded.add(serie);
        }
        rs.close();
        return serieFinded;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllT() {
        return null;
    }

    @Override
    public List<Serie> getAll() throws SQLException {
        ArrayList<Serie> serieFinded = new ArrayList<>();
        try {
            String query = "SELECT cods FROM serie;";
            ResultSet rs = connessione.executeSearch(query);
            Serie serie;
            while (rs.next()) {
                serie = get(rs.getInt(1));
                serieFinded.add(serie);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serieFinded;
    }

    @Override
    public void insert(Serie serie) {
        try {
            int cods = serie.getCods();
            String nome = serie.getNome();
            String query = "INSERT INTO Serie VALUES (" + cods + ",'" + nome + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Serie serie) throws SQLException {
        try {
            int cods = serie.getCods();
            String nome = serie.getNome();
            boolean completata = serie.getCompletata();
            String query = "UPDATE Serie SET nome = " + nome + ", completata = " + completata + " WHERE cods = " + cods + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Serie serie) throws SQLException {
        try {
            int cods = serie.getCods();
            String query = "DELETE FROM Serie WHERE cods = " + cods + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
