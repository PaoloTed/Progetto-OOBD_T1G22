package com.example.bibliotecadigitale.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {
    List<T> getAll() throws SQLException;

    void insert(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;

    ArrayList<T> getRicerca(String tipoRicerca, String parolaChiave) throws SQLException;
}
