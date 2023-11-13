package com.example.bibliotecadigitale.Connection;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SerieDAO {
    public ArrayList<String> findSerie(ArrayList<Integer> codSerie){
        ArrayList<String> serie = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            for (int i = 0; i < codSerie.size(); i++) {
                String query = "SELECT nome FROM serie WHERE codS = " + codSerie.get(i) + ";";
                ResultSet rs = connessione.executeSearch(query);
                while (rs.next()){
                    serie.add(rs.getString(1));
                }
                rs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return serie;
    }
}
