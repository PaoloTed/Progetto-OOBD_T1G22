package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.UtenteDAO;
import com.example.bibliotecadigitale.Model.Utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static com.example.bibliotecadigitale.Model.Utente.getUtente;

public class UtenteDAOImpl implements UtenteDAO {

    private final Connessione connessione = new Connessione();
    private final Connection conn = Connessione.getConnection();

    @Override
    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(ArrayList<String> strings) throws SQLException {

    }

    @Override
    public void update(ArrayList<String> strings) throws SQLException {

    }

    @Override
    public void delete(ArrayList<String> strings) throws SQLException {

    }

    @Override
    public ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> get(String emailUser) throws SQLException {
        return null;
    }
}
