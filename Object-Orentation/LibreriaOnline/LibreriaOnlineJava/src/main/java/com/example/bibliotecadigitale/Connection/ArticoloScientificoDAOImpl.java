package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.ArticoloScientificoDAO;
import com.example.bibliotecadigitale.Model.ArticoloScientifico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticoloScientificoDAOImpl implements ArticoloScientificoDAO {
    private final Connessione connessione = new Connessione();

    @Override
    public ArrayList<ArticoloScientifico> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<ArticoloScientifico> articoloScientificoFinded = new ArrayList<>();
        String query;

        if (tipoRicerca.equalsIgnoreCase("numpagine") || tipoRicerca.equalsIgnoreCase("conferenza")) {
            query = "SELECT doi FROM articolo_scientifico WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT doi FROM articolo_scientifico WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        ArticoloScientifico articolo;
        while (rs.next()) {
            articolo = get(rs.getString(1));
            articoloScientificoFinded.add(articolo);
        }
        rs.close();

        return articoloScientificoFinded;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllT() {
        return null;
    }

    @Override
    public ArticoloScientifico get(String doi) throws SQLException {
        ArticoloScientifico articolo;
        try {
            String query = "SELECT * FROM articolo_scientifico WHERE doi = '" + doi + "';";
            ResultSet rs = connessione.executeSearch(query);
            articolo = new ArticoloScientifico();
            while (rs.next()) {
                articolo.setDoi(rs.getString(1));
                articolo.setTitolo(rs.getString(2));
                articolo.setGenere(rs.getString(3));
                articolo.setNumpagine(rs.getInt(4));
                articolo.setDatauscita(rs.getString(5));
                articolo.setDescrizione(rs.getString(6));
                articolo.setFruizione(rs.getString(7));
                articolo.setEditore(rs.getString(8));
                articolo.setAutore(rs.getString(9));
                articolo.setLingua(rs.getString(10));
                articolo.setConferenza(rs.getObject(11, Integer.class));
                articolo.setNomer(rs.getString(12));
                articolo.setDatar(rs.getString(13));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articolo;
    }

    @Override
    public List<ArticoloScientifico> getAll() throws SQLException {
        ArrayList<ArticoloScientifico> articoloScientificoFinded = new ArrayList<>();
        try {
            String query = "SELECT doi FROM articolo_scientifico;";
            ResultSet rs = connessione.executeSearch(query);
            ArticoloScientifico articolo;
            while (rs.next()) {
                articolo = get(rs.getString(1));
                articoloScientificoFinded.add(articolo);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articoloScientificoFinded;
    }

    @Override
    public void insert(ArticoloScientifico articoloScientifico) throws SQLException {
        try {
            String conferenza = "NULL";
            String dataUscita = "NULL";
            String descrizione = articoloScientifico.getDescrizione().replace("'", "''");

            if (articoloScientifico.getConferenza() != null) {
                conferenza = Integer.toString(articoloScientifico.getConferenza());
            }
            if (articoloScientifico.getDatauscita() != null) {
                dataUscita = "'" + articoloScientifico.getDatauscita() + "'";
            }
            String query = "INSERT INTO articolo_scientifico VALUES ('" + articoloScientifico.getDoi() +
                    "', '" + articoloScientifico.getTitolo() +
                    "', '" + articoloScientifico.getGenere() +
                    "', " + articoloScientifico.getNumpagine() +
                    ", " + dataUscita +
                    ", '" + descrizione +
                    "', '" + articoloScientifico.getFruizione() +
                    "', '" + articoloScientifico.getEditore() +
                    "', '" + articoloScientifico.getAutore() +
                    "', '" + articoloScientifico.getLingua() +
                    "', " + conferenza +
                    ", '" + articoloScientifico.getNomer() +
                    "', '" + articoloScientifico.getDatar() +
                    "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(ArticoloScientifico articoloScientifico) throws SQLException {
        try {
            String numPagine = "NULL";
            String conferenza = "NULL";
            String dataUscita = "NULL";
            String datar = "NULL";
            String nomer = "NULL";
            String descrizione = articoloScientifico.getDescrizione().replace("'", "''");
            if (articoloScientifico.getNumpagine() != 0) {
                numPagine = Integer.toString(articoloScientifico.getNumpagine());
            }
            if (articoloScientifico.getConferenza() != null) {
                conferenza = Integer.toString(articoloScientifico.getConferenza());
            }
            if (articoloScientifico.getDatauscita() != null) {
                dataUscita = "'" + articoloScientifico.getDatauscita() + "'";
            }
            if (articoloScientifico.getDatar() != null) {
                datar = "'" + articoloScientifico.getDatar() + "'";
            }
            if (articoloScientifico.getNomer() != null) {
                nomer = "'" + articoloScientifico.getNomer() + "'";
            }
            String query = "UPDATE articolo_scientifico SET doi = '" + articoloScientifico.getDoi() +
                    "', titolo = '" + articoloScientifico.getTitolo() +
                    "', genere = '" + articoloScientifico.getGenere() +
                    "', numpagine = " + numPagine +
                    ", datauscita = " + dataUscita +
                    ", descrizione = '" + descrizione +
                    "', fruizione = '" + articoloScientifico.getFruizione() +
                    "', editore = '" + articoloScientifico.getEditore() +
                    "', autore = '" + articoloScientifico.getAutore() +
                    "', lingua = '" + articoloScientifico.getLingua() +
                    "', conferenza = " + conferenza +
                    ", nomer = " + nomer +
                    ", datar = " + datar +
                    " WHERE doi = '" + articoloScientifico.getDoi() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(ArticoloScientifico articoloScientifico) throws SQLException {
        try {
            String query = "DELETE FROM articolo_scientifico WHERE doi = '" + articoloScientifico.getDoi() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
