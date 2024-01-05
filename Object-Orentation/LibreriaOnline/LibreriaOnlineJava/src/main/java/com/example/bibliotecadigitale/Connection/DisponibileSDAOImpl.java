package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileSDAO;
import com.example.bibliotecadigitale.Model.DisponibileS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileSDAOImpl implements DisponibileSDAO {
    @Override
    public DisponibileS get(int coda, int cods) throws SQLException {
        DisponibileS disponibileS;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM disponibile_s WHERE coda = '" + coda + "' AND cods = '" + cods + "';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileS = new DisponibileS();
            while (rs.next()) {
                disponibileS.setCodA(rs.getInt(1));
                disponibileS.setCodS(rs.getInt(2));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileS;
    }

    public ArrayList<DisponibileS> getRicerca(String tipoRicerca, String parolaChiave) {
        ArrayList<DisponibileS> disponibileSFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT coda,cods FROM disponibile_s WHERE " + tipoRicerca + " = " + parolaChiave + ";";
            ResultSet rs = connessione.executeSearch(query);
            DisponibileS disponibileS;
            while (rs.next()) {
                disponibileS = get(rs.getInt(1), rs.getInt(2));
                disponibileSFinded.add(disponibileS);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return disponibileSFinded;
    }

    @Override
    public DisponibileS get(String cod) throws SQLException {
        return null;
    }

    @Override
    public List<DisponibileS> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(DisponibileS disponibileS) throws SQLException {

    }

    @Override
    public void update(DisponibileS disponibileS) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "UPDATE disponibile_s SET cods = '" + disponibileS.getCodS() + "' WHERE coda = '" + disponibileS.getCodA() + "';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(DisponibileS disponibileS) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "DELETE FROM disponibile_s WHERE coda = '" + disponibileS.getCodA() + "' AND cods = '" + disponibileS.getCodS() + "';";
            connessione.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
