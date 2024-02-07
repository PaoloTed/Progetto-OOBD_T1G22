package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.DisponibileADAO;
import com.example.bibliotecadigitale.Model.DisponibileA;
import com.example.bibliotecadigitale.Model.DisponibileL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibileADAOImpl implements DisponibileADAO {
    @Override
    public DisponibileA get(int coda, String doi) throws SQLException {
        DisponibileA disponibileA;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT coda,doi FROM disponibile_a WHERE coda = " + coda + " AND doi = '" + doi +"';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileA = new DisponibileA();
            while (rs.next()) {
                disponibileA.setCodA(rs.getInt(1));
                disponibileA.setDoi(rs.getString(2));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



    public ArrayList<DisponibileA> getAquisti(String doi) throws SQLException {
        ArrayList<DisponibileA> disponibileA;
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT * FROM disponibile_a WHERE coda = AND doi = '" + doi +"';";
            ResultSet rs = connessione.executeSearch(query);
            disponibileA = new ArrayList();
            while (rs.next()) {
                disponibileA.add(get(rs.getInt(1),rs.getString(2)));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileA;
    }


    public ArrayList<DisponibileA> getRicerca(String tipoRicerca, String parolaChiave){
        ArrayList<DisponibileA> disponibileAFinded = new ArrayList<>();
        String query = "";
        try {
            Connessione connessione = new Connessione();
            //Controllo dovuto alla non possibilita di usare %% con un intero
            if(tipoRicerca.equalsIgnoreCase("coda")) {
                query = "SELECT coda,doi FROM disponibile_a WHERE coda = '"+parolaChiave+"';";
            }
            if(tipoRicerca.equalsIgnoreCase("doi")) {
                query = "SELECT coda,doi FROM disponibile_a WHERE doi LIKE LOWER('%"+parolaChiave+"%');";
            }
            ResultSet rs = connessione.executeSearch(query);
            DisponibileA disponibileA;
            while (rs.next()) {
                disponibileA = get(rs.getInt(1),rs.getString(2));
                disponibileAFinded.add(disponibileA);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return disponibileAFinded;
    }

    @Override
    public DisponibileA get(String cod) throws SQLException {
        return null;
    }

    @Override
    public List<DisponibileA> getAll() throws SQLException {
        ArrayList<DisponibileA> disponibileAFinded = new ArrayList<>();
        try {
            Connessione connessione = new Connessione();
            String query = "SELECT coda,doi FROM disponibile_a;";
            ResultSet rs = connessione.executeSearch(query);
            DisponibileA disponibileA;
            while (rs.next()) {
                disponibileA = get(rs.getInt(1),rs.getString(2));
                disponibileAFinded.add(disponibileA);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disponibileAFinded;
    }

    @Override
    public void insert(DisponibileA disponibileA) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "INSERT INTO disponibile_a VALUES (" + disponibileA.getCodA() + ",'" + disponibileA.getDoi() + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(DisponibileA disponibileA) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "UPDATE disponibile_a SET coda = " + disponibileA.getCodA() + ", doi = '" + disponibileA.getDoi() + "' WHERE coda = " + disponibileA.getCodA() + " AND doi = '" + disponibileA.getDoi() +"';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(DisponibileA disponibileA) throws SQLException {
        try {
            Connessione connessione = new Connessione();
            String query = "DELETE FROM disponibile_a WHERE coda = " + disponibileA.getCodA() + " AND doi = '" + disponibileA.getDoi() +"';";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public DisponibileA get(String coda, String doi) throws SQLException {
        return null;
    }
}
