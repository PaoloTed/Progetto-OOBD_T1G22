package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.Libro;

import java.sql.SQLException;

public interface LibroDAO extends DAO<Libro> {
    Libro get(String isbn) throws SQLException;
}
