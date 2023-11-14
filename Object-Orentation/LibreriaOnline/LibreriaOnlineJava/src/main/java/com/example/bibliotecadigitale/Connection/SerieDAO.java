package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.Model.Serie;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SerieDAO {
    public ArrayList<String> findSerieFromCode(ArrayList<Integer> codSerie) {
        ArrayList<String> serie = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            for (int i = 0; i < codSerie.size(); i++) {
                String query = "SELECT nome FROM serie WHERE codS = " + codSerie.get(i) + ";";
                ResultSet rs = connessione.executeSearch(query);
                while (rs.next()) {
                    serie.add(rs.getString(1));
                }
                rs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return serie;
    }

    public ArrayList<Integer> findSerieFromName(ArrayList<String> nomeSerie) {
        ArrayList<Integer> serie = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            for (int i = 0; i < nomeSerie.size(); i++) {
                String query = "SELECT codS FROM serie WHERE nome = '" + nomeSerie.get(i) + "';";
                ResultSet rs = connessione.executeSearch(query);
                while (rs.next()) {
                    serie.add(rs.getInt(1));
                }
                rs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return serie;
    }

    public Serie findSerieFromNamev2(String nomeSerieToFind) {
        Serie serieFinded = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM serie WHERE nome = '" + nomeSerieToFind + "';";
            ResultSet rs = connessione.executeSearch(query);
            serieFinded = new Serie();
            while (rs.next()) {
                serieFinded.setCodS(rs.getInt(1));
                serieFinded.setNome(rs.getString(2));
            }
            rs.close();
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return serieFinded;
    }

    public Serie findSerieFromCodev2(int codSerieToFind) {
        Serie serieFinded = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM serie WHERE codS = '" + codSerieToFind + "';";
            ResultSet rs = connessione.executeSearch(query);
            serieFinded = new Serie();
            while (rs.next()) {
                serieFinded.setCodS(rs.getInt(1));
                serieFinded.setNome(rs.getString(2));
            }
            rs.close();
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return serieFinded;
    }
}
