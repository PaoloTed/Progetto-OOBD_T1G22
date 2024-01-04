package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.AcquistoDAO;
import com.example.bibliotecadigitale.Model.Acquisto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcquistoDAOImpl implements AcquistoDAO {
    @Override
    public Acquisto get(String cod) throws SQLException {
        Acquisto acquisto;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM acquisto WHERE cod = '" + cod + "';";
            ResultSet rs = connessione.executeSearch(query);
            acquisto = new Acquisto();
            while (rs.next()) {
                acquisto.setCodA(rs.getInt(1));
                acquisto.setNome(rs.getString(2));
                acquisto.setTipo(rs.getString(3));
                acquisto.setUrl(rs.getString(4));
                acquisto.setIndirizzo(rs.getString(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return acquisto;
    }
    public ArrayList<Acquisto> getRicerca(String tipoRicerca,String parolaChiave){
        ArrayList<Acquisto> acquistoFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT cod FROM acquisto WHERE LOWER("+tipoRicerca+") LIKE LOWER('%"+parolaChiave+"%');";
            ResultSet rs = connessione.executeSearch(query);
            Acquisto acquisto;
            while (rs.next()) {
                acquisto = get(rs.getString(1));
                acquistoFinded.add(acquisto);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return acquistoFinded;
    }

    @Override
    public ArrayList<Acquisto> getAll() throws SQLException {
        ArrayList<Acquisto> acquistoFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT cod FROM acquisto;";
            ResultSet rs = connessione.executeSearch(query);
            Acquisto acquisto;
            while (rs.next()) {
                acquisto = get(rs.getString(1));
                acquistoFinded.add(acquisto);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return acquistoFinded;
    }

    @Override
    public void save(Acquisto acquisto) throws SQLException {

    }

    @Override
    public void update(Acquisto acquisto) throws SQLException {

    }

    @Override
    public void delete(Acquisto acquisto) throws SQLException {

    }
}
