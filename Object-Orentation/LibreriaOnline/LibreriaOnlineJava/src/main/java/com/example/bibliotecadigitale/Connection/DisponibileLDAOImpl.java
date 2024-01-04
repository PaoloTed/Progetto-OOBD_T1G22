package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileLDAO;
import com.example.bibliotecadigitale.Model.DisponibileL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileLDAOImpl implements DisponibileLDAO {
    @Override
    public DisponibileL get(String cod) throws SQLException {
        DisponibileL disponibileL;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM disponibile_l WHERE cod = '" + cod + "';";
            disponibileL = new DisponibileL();
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                disponibileL.setCodA(rs.getString(1));
                disponibileL.setIsbn(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileL;
    }

    public ArrayList<DisponibileL> getRicerca(String tipoRicerca,String parolaChiave){
        ArrayList<DisponibileL> disponibileLFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT cod FROM disponibile_l WHERE LOWER("+tipoRicerca+") LIKE LOWER('%"+parolaChiave+"%');";
            ResultSet rs = connessione.executeSearch(query);
            DisponibileL disponibileL;
            while (rs.next()) {
                disponibileL = get(rs.getString(1));
                disponibileLFinded.add(disponibileL);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return disponibileLFinded;
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

    }

    @Override
    public void delete(DisponibileL disponibileL) throws SQLException {

    }
}
