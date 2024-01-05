package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileLDAO;
import com.example.bibliotecadigitale.Model.DisponibileL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileLDAOImpl implements DisponibileLDAO {
    @Override
    public DisponibileL get(int coda,String isbn ) throws SQLException {
        DisponibileL disponibileL;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM disponibile_l WHERE coda = '" + coda + "'AND isbn = '" + isbn +"';";
            disponibileL = new DisponibileL();
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                disponibileL.setCodA(rs.getInt(1));
                disponibileL.setIsbn(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileL;
    }

    public ArrayList<DisponibileL> getRicerca(String tipoRicerca,String parolaChiave){
        ArrayList<DisponibileL> disponibileLFinded = new ArrayList<>();
        String query = "";
        try {
            Connessione connessione = new Connessione();
            if(tipoRicerca.equalsIgnoreCase("coda")) {
                query = "SELECT coda,isbn FROM disponibile_l WHERE "+tipoRicerca+"= "+parolaChiave+";";
            }
            if(tipoRicerca.equalsIgnoreCase("isbn")) {
                query = "SELECT coda,isbn FROM disponibile_l WHERE "+tipoRicerca+" LIKE LOWER('%"+parolaChiave+"%');";
            }
            ResultSet rs = connessione.executeSearch(query);
            DisponibileL disponibileL;
            while (rs.next()) {
                disponibileL = get(rs.getInt(1),rs.getString(2));
                disponibileLFinded.add(disponibileL);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return disponibileLFinded;
    }

    @Override
    public DisponibileL get(String cod) throws SQLException {
        return null;
    }

    @Override
    public List<DisponibileL> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(DisponibileL disponibileL) throws SQLException {

    }

    @Override
    public void update(DisponibileL disponibileL) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "UPDATE disponibile_l SET isbn = '" + disponibileL.getIsbn() + "' WHERE coda = '" + disponibileL.getCodA() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(DisponibileL disponibileL) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "DELETE FROM disponibile_l WHERE coda = '" + disponibileL.getCodA() + "'AND isbn = '" + disponibileL.getIsbn() +"';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
