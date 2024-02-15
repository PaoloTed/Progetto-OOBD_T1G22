package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.AcquistoDAO;
import com.example.bibliotecadigitale.Model.Acquisto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcquistoDAOImpl implements AcquistoDAO {

    private final Connessione connessione = new Connessione();

    @Override
    public Acquisto get(int coda) throws SQLException {
        Acquisto acquisto;
        try {
            String query = "SELECT * FROM acquisto WHERE coda = " + coda + ";";
            ResultSet rs = connessione.executeSearch(query);
            acquisto = new Acquisto();
            while (rs.next()) {
                acquisto.setCoda(rs.getInt(1));
                acquisto.setNome(rs.getString(2));
                acquisto.setTipoa(rs.getString(3));
                acquisto.setUrl(rs.getString(4));
                acquisto.setIndirizzo(rs.getString(5));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return acquisto;
    }

    @Override
    public ArrayList<Acquisto> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<Acquisto> acquistoFinded = new ArrayList<>();
        String query;

        if (tipoRicerca.equalsIgnoreCase("coda")) {
            query = "SELECT coda FROM acquisto WHERE coda = " + parolaChiave + ";";
        } else {
            query = "SELECT coda FROM acquisto WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        Acquisto acquisto;
        while (rs.next()) {
            acquisto = get(rs.getInt(1));
            acquistoFinded.add(acquisto);
        }
        rs.close();

        return acquistoFinded;
    }

    @Override
    public ArrayList<Acquisto> getAll() throws SQLException {
        ArrayList<Acquisto> acquistoFinded = new ArrayList<>();
        try {
            String query = "SELECT coda FROM acquisto;";
            ResultSet rs = connessione.executeSearch(query);
            Acquisto acquisto;
            while (rs.next()) {
                acquisto = get(rs.getInt(1));
                acquistoFinded.add(acquisto);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return acquistoFinded;
    }

    @Override
    public void insert(Acquisto acquisto) throws SQLException {
        try {
            String query = "INSERT INTO acquisto VALUES (" + acquisto.getCoda() + ",'" + acquisto.getNome() + "','" + acquisto.getTipoa() + "','" + acquisto.getUrl() + "','" + acquisto.getIndirizzo() + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Acquisto acquisto) throws SQLException {
        try {
            String query = "UPDATE acquisto SET coda = " + acquisto.getCoda() + ", nome = '" + acquisto.getNome() + "', tipoa = '" + acquisto.getTipoa() + "', url = '" + acquisto.getUrl() + "', indirizzo = '" + acquisto.getIndirizzo() + "' WHERE coda = " + acquisto.getCoda() + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Acquisto acquisto) throws SQLException {
        try {
            String query = "DELETE FROM acquisto WHERE coda = " + acquisto.getCoda() + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
