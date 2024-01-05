package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.DisponibileL;

import java.sql.SQLException;

public interface DisponibileLDAO extends DAO<DisponibileL>{
    DisponibileL get(int coda, String isbn) throws SQLException;
}
