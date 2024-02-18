package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.PresentazioneDAO;
import com.example.bibliotecadigitale.Model.Presentazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PresentazioneDAOImpl implements PresentazioneDAO {
    private final Connessione connessione = new Connessione();

    @Override
    public Presentazione get(int codP) {
        Presentazione presentazione = null;
        try {
            String query = "SELECT * FROM Presentazione WHERE codp = " + codP + ";";
            ResultSet rs = connessione.executeSearch(query);
            while (rs.next()) {
                presentazione = new Presentazione();
                presentazione.setCodp(rs.getInt(1));
                presentazione.setNome(rs.getString(2));
                presentazione.setIndirizzo(rs.getString(3));
                presentazione.setDatapresentazione(rs.getString(4));
                presentazione.setTipo(rs.getString(5));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return presentazione;
    }

    @Override
    public ArrayList<Presentazione> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        ArrayList<Presentazione> presentazioneFinded = new ArrayList<>();
        String query;
        if (tipoRicerca.equalsIgnoreCase("codp")) {
            query = "SELECT codp FROM presentazione WHERE " + tipoRicerca + " = " + parolaChiave + ";";
        } else {
            query = "SELECT codp FROM presentazione WHERE " + tipoRicerca + " LIKE '%" + parolaChiave + "%';";
        }
        ResultSet rs = connessione.executeSearch(query);
        Presentazione presentazione;
        while (rs.next()) {
            presentazione = get(rs.getInt(1));
            presentazioneFinded.add(presentazione);
        }
        rs.close();

        return presentazioneFinded;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllT() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> getRicercaT(String modRicerca, String titoloRicerche) throws SQLException {
        return null;
    }

    @Override
    public void deleteT(ArrayList<String> elemento) throws SQLException {

    }

    @Override
    public void insertT(ArrayList<String> elemento) throws SQLException {

    }

    @Override
    public void updateT(ArrayList<String> elemento) throws SQLException {

    }

    @Override
    public List<Presentazione> getAll() throws SQLException {
        ArrayList<Presentazione> presentazioneFinded = new ArrayList<>();
        try {
            String query = "SELECT codp FROM presentazione;";
            ResultSet rs = connessione.executeSearch(query);
            Presentazione presentazione;
            while (rs.next()) {
                presentazione = get(rs.getInt(1));
                presentazioneFinded.add(presentazione);
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return presentazioneFinded;
    }

    @Override
    public void insert(Presentazione presentazione) throws SQLException {
        try {
            int codp = presentazione.getCodp();
            String nome = presentazione.getNome();
            String indirizzo = presentazione.getIndirizzo();
            String dataPresentazione = presentazione.getDatapresentazione();
            String tipo = presentazione.getTipo();
            String query = "INSERT INTO presentazione VALUES (" + codp + ", '" + nome + "', '" + indirizzo + "', '" + dataPresentazione + "', '" + tipo + "');";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Presentazione presentazione) throws SQLException {
        try {
            int codp = presentazione.getCodp();
            String nome = presentazione.getNome();
            String indirizzo = presentazione.getIndirizzo();
            String dataPresentazione = presentazione.getDatapresentazione();
            String tipo = presentazione.getTipo();
            String query = "UPDATE presentazione SET nome = '" + nome + "', indirizzo = '" + indirizzo + "', dataPresentazione = '" + dataPresentazione + "', tipo = '" + tipo + "' WHERE codp = " + codp + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Presentazione presentazione) throws SQLException {
        try {
            int codp = presentazione.getCodp();
            String query = "DELETE FROM presentazione WHERE codp = " + codp + ";";
            connessione.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
