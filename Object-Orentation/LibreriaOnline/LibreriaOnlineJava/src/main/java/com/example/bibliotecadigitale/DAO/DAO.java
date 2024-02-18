package com.example.bibliotecadigitale.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAO {
    ArrayList<ArrayList<String>> getAll() throws SQLException;
    void insert(ArrayList<String> t) throws SQLException;
    void update(ArrayList<String> string) throws SQLException;
    void delete(ArrayList<String> t) throws SQLException;
    ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException;
}
