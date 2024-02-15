package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileSDAO;
import com.example.bibliotecadigitale.Model.DisponibileS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileSDAOImpl implements DisponibileSDAO {
    private final Connessione connessione = new Connessione();

    @Override
    public DisponibileS get(int coda, int cods) throws SQLException {
        DisponibileS disponibileS;
        try {
            String query = "SELECT * FROM disponibile_s WHERE coda = " + coda + " AND cods = " + cods + ";";
            ResultSet rs = connessione.executeSearch(query);
            disponibileS = new DisponibileS();
            while (rs.next()) {
                disponibileS.setCoda(rs.getInt(1));
                disponibileS.setCods(rs.getInt(2));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileS;
    }

    @Override
    public ArrayList<DisponibileS> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<DisponibileS> disponibileSFinded = new ArrayList<>();
        String query = "SELECT coda,cods FROM disponibile_s WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        ResultSet rs = connessione.executeSearch(query);
        DisponibileS disponibileS;
        while (rs.next()) {
            disponibileS = get(rs.getInt(1), rs.getInt(2));
            disponibileSFinded.add(disponibileS);
        }
        rs.close();
        return disponibileSFinded;
    }

    @Override
    public List<DisponibileS> getAll() throws SQLException {
        ArrayList<DisponibileS> disponibileSFinded = new ArrayList<>();
        try {
            String query = "SELECT coda,cods FROM disponibile_s;";
            ResultSet rs = connessione.executeSearch(query);
            DisponibileS disponibileS;
            while (rs.next()) {
                disponibileS = get(rs.getInt(1), rs.getInt(2));
                disponibileSFinded.add(disponibileS);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileSFinded;
    }

    @Override
    public void insert(DisponibileS disponibileS) throws SQLException {
        try {
            String query = "INSERT INTO disponibile_s (coda,cods) VALUES (" + disponibileS.getCoda() + "," + disponibileS.getCods() + ");";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DisponibileS disponibileS) throws SQLException {
        try {
            String query = "UPDATE disponibile_s SET cods = " + disponibileS.getCods() + " WHERE coda = " + disponibileS.getCoda() + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(DisponibileS disponibileS) throws SQLException {
        try {
            String query = "DELETE FROM disponibile_s WHERE coda = " + disponibileS.getCoda() + " AND cods = '" + disponibileS.getCods() + "';";
            connessione.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
