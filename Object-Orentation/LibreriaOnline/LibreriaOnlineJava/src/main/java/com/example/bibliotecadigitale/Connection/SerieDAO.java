package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.Model.Serie;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SerieDAO {
    public Serie findSerieFromCodev2(int codSerieToFind) {
        Serie serieFinded = null;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM serie WHERE codS = '" + codSerieToFind + "';";
            ResultSet rs = connessione.executeSearch(query);
            serieFinded = new Serie();
            while (rs.next()) {
                serieFinded.setCodS(rs.getString(1));
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
