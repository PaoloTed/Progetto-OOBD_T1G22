package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Libro;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LibroDAO extends DAO {
    ArrayList<String> get(String isbn) throws SQLException;
}
