package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.ArticoloScientificoDAO;
import com.example.bibliotecadigitale.Model.ArticoloScientifico;
import com.example.bibliotecadigitale.Model.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticoloScientificoDAOImpl implements ArticoloScientificoDAO {
    public ArrayList<ArticoloScientifico> getRicerca(String tipoRicerca, String parolaChiave) {
        ArrayList<ArticoloScientifico> articoloScientificoFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT doi FROM articolo_scientifico WHERE LOWER(" + tipoRicerca + ") LIKE LOWER('%" + parolaChiave + "%');";
            ResultSet rs = connessione.executeSearch(query);
            ArticoloScientifico articolo;
            while (rs.next()) {
                articolo = get(rs.getString(1));
                articoloScientificoFinded.add(articolo);
            }
            rs.close();
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return articoloScientificoFinded;
    }

    @Override
    public ArticoloScientifico get(String doi) throws SQLException {
        ArticoloScientifico articolo;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM articolo_scientifico WHERE doi = '" + doi + "';";
            ResultSet rs = connessione.executeSearch(query);
            articolo = new ArticoloScientifico();
            while (rs.next()) {
                articolo.setDoi(rs.getString(1));
                articolo.setTitolo(rs.getString(2));
                articolo.setGenere(rs.getString(3));
                articolo.setNumPagine(rs.getInt(4));
                articolo.setDataUscita(rs.getString(5));
                articolo.setDescrizione(rs.getString(6));
                articolo.setFruizione(rs.getString(7));
                articolo.setEditore(rs.getString(8));
                articolo.setAutore(rs.getString(9));
                articolo.setLingua(rs.getString(10));
                articolo.setConferenza(rs.getInt(11));
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
        return null;
    }

    @Override
    public void save(ArticoloScientifico articoloScientifico) throws SQLException {

    }

    @Override
    public void update(ArticoloScientifico articoloScientifico) throws SQLException {

    }

    @Override
    public void delete(ArticoloScientifico articoloScientifico) throws SQLException {

    }
}
