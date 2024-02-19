package com.example.bibliotecadigitale.Connection;

import com.example.bibliotecadigitale.DAO.ArticoloScientificoDAO;
import com.example.bibliotecadigitale.Model.ArticoloScientifico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticoloScientificoDAOImpl implements ArticoloScientificoDAO {
    private final Connessione connessione = new Connessione();
    private final Connection conn = Connessione.getConnection();

    @Override
    public ArrayList<String> get(String doi) throws SQLException {

    }

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
}
