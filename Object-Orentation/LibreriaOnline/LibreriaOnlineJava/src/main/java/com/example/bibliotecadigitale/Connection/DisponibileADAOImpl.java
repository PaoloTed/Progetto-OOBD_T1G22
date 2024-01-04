package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileADAO;
import com.example.bibliotecadigitale.Model.DisponibileA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileADAOImpl implements DisponibileADAO {
    @Override
    public DisponibileA get(String cod) throws SQLException {
        DisponibileA disponibileA;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM disponibile_a WHERE cod = '" + cod + "';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileA = new DisponibileA();
            while (rs.next()) {
                disponibileA.setCodA(rs.getString(1));
                disponibileA.setDoi(rs.getString(2));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public ArrayList<DisponibileA> getRicerca(String tipoRicerca, String parolaChiave){
        ArrayList<DisponibileA> disponibileAFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT cod FROM disponibile_a WHERE LOWER("+tipoRicerca+") LIKE LOWER('%"+parolaChiave+"%');";
            ResultSet rs = connessione.executeSearch(query);
            DisponibileA disponibileA;
            while (rs.next()) {
                disponibileA = get(rs.getString(1));
                disponibileAFinded.add(disponibileA);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return disponibileAFinded;
    }

    @Override
    public List<DisponibileA> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(DisponibileA disponibileA) throws SQLException {

    }

    @Override
    public void update(DisponibileA disponibileA) throws SQLException {

    }

    @Override
    public void delete(DisponibileA disponibileA) throws SQLException {

    }
}
