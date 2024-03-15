package com.example.bibliotecadigitale.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAO {
    ArrayList<ArrayList<String>> getAll() throws SQLException;
    void insert(ArrayList<String> strings) throws SQLException;
    void update(ArrayList<String> strings) throws SQLException;
    void delete(ArrayList<String> strings) throws SQLException;
    ArrayList<ArrayList<String>> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException;
}
