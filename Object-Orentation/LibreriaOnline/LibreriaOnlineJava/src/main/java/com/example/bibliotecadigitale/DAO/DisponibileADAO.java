package com.example.bibliotecadigitale.DAO;

import com.example.bibliotecadigitale.Model.DisponibileA;

import java.sql.SQLException;

public interface DisponibileADAO extends DAO<DisponibileA>{
    DisponibileA get(int coda, String doi) throws SQLException;

    DisponibileA get(String coda, String doi) throws SQLException;
}
