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
        String query = "";
        try {
            Connessione connessione = new Connessione();
            if(tipoRicerca.equalsIgnoreCase("numpagine")||tipoRicerca.equalsIgnoreCase("conferenza")){
                query = "SELECT doi FROM articolo_scientifico WHERE " + tipoRicerca + " = " + parolaChiave + ";";
            }else{
                query = "SELECT doi FROM articolo_scientifico WHERE " + tipoRicerca + " LIKE LOWER('%" + parolaChiave + "%');";
            }
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
                if(rs.getInt(11) > 0){
                    articolo.setConferenza(rs.getInt(11));
                }
                System.out.println(rs.getInt(11));
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
        try {
            Connessione connessione = new Connessione();

            String numPagine = "NULL";
            String conferenza = "NULL";
            String dataUscita = "NULL";
            String descrizione = articoloScientifico.getDescrizione().replace("'", "''");
            if(articoloScientifico.getNumPagine() != 0) {
                numPagine = Integer.toString(articoloScientifico.getNumPagine());
            }
            if(articoloScientifico.getConferenza() != 0) {
                conferenza = Integer.toString(articoloScientifico.getConferenza());
            }
            if(articoloScientifico.getDataUscita() != null) {
                dataUscita = "'" + articoloScientifico.getDataUscita() + "'";
            }
            String query = "UPDATE articolo_scientifico SET doi = " + articoloScientifico.getDoi() +
                    ", titolo = '" + articoloScientifico.getTitolo() +
                    "', genere = '" + articoloScientifico.getGenere() +
                    "', numpagine = " + numPagine +
                    ", datauscita = " + dataUscita +
                    ", descrizione = '" + descrizione +
                    "', fruizione = '" + articoloScientifico.getFruizione() +
                    "', editore = '" + articoloScientifico.getEditore() +
                    "', autore = '" + articoloScientifico.getAutore() +
                    "', lingua = '" + articoloScientifico.getLingua() +
                    "', conferenza = " + conferenza +
                    ", nomer = '" + articoloScientifico.getNomer() +
                    "', datar = '" + articoloScientifico.getDatar() +
                    "' WHERE doi = " + articoloScientifico.getDoi() + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(ArticoloScientifico articoloScientifico) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "DELETE FROM articolo_scientifico WHERE doi = '" + articoloScientifico.getDoi() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
