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
                acquisto.setCodA(rs.getInt(1));
                acquisto.setNome(rs.getString(2));
                acquisto.setTipoA(rs.getString(3));
                acquisto.setUrl(rs.getString(4));
                acquisto.setIndirizzo(rs.getString(5));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return acquisto;
    }
    public ArrayList<Acquisto> getRicerca(String tipoRicerca,String parolaChiave){
        ArrayList<Acquisto> acquistoFinded = new ArrayList<>();
        String query;
        try {
            if(tipoRicerca.equalsIgnoreCase("coda")){
                query = "SELECT coda FROM acquisto WHERE coda = " + parolaChiave + ";";
            }else {
                query = "SELECT coda FROM acquisto WHERE LOWER(" + tipoRicerca + ") LIKE LOWER('%" + parolaChiave + "%');";
            }
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
    public Acquisto get(String cod) throws SQLException {
        return null;
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
            String query = "INSERT INTO acquisto VALUES (" + acquisto.getCodA() + ",'" + acquisto.getNome() + "','" + acquisto.getTipoA() + "','" + acquisto.getUrl() + "','" + acquisto.getIndirizzo() + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Acquisto acquisto) throws SQLException {
        try {
            String query = "UPDATE acquisto SET coda = " + acquisto.getCodA() + ", nome = '" + acquisto.getNome() + "', tipoa = '" + acquisto.getTipoA() + "', url = '" + acquisto.getUrl() + "', indirizzo = '" + acquisto.getIndirizzo() + "' WHERE coda = " + acquisto.getCodA() + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Acquisto acquisto) throws SQLException {
        try {
            String query = "DELETE FROM acquisto WHERE coda = " + acquisto.getCodA() + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
